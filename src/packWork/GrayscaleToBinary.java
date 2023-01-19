package packWork;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

//functie folosita pentru conversia in binar
public class GrayscaleToBinary extends ReadImage {

	private String fileName;
	private ReadImage readImage;
	private BufferedImage grayscaleImage;
	static int threshold;
	
	
	static{
		 threshold = 127; //pragul static care reprezinta jumatatea lui 255
	}
	//constructorul cu parametrii
	//seteaza numele fisierului de intrare
	public GrayscaleToBinary( String fileName) {
		super();
		this.fileName = fileName;
		
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//functie folosita pentru reconstruirea imaginii dupa ce citesc sferturile
	public BufferedImage reconstructImage(BufferedImage[] image){
		
		int width = 2*image[0].getWidth();
		int height = 2*image[0].getHeight();
		
		BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = finalImage.getGraphics();

		g.drawImage(image[0], 0, 0, null);
		g.drawImage(image[1], width/2, 0, null);
		g.drawImage(image[2], 0, height/2, null);
		g.drawImage(image[3], width/2, height/2, null);
		
		return finalImage;
	}
	
	//functie pentru transformarea imaginii in binar
	public BufferedImage processImage(BufferedImage grayscaleImage ){
		long startTime = System.nanoTime();
		
		int width = grayscaleImage.getWidth();
		int height = grayscaleImage.getHeight();
		
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Color c = new Color(grayscaleImage.getRGB(i,  j));//descompunerea culorilor imaginii in valorile RGB
				
				int red = (int)(c.getRed());
				int green = (int)(c.getGreen());
				int blue = (int)(c.getBlue());
				
				int grayScale = (red+green+blue)/3; // se obtine valoarea medie daca setam pixelii la aceasta culoare vom avea nivelul de gri pentru fiecare pixel
						
				//conversia la binar
				//setam pixelii in alb sau negru
				if( grayScale < threshold){ 
					//setare culoarea negru
					red = 0;
					green = 0;
					blue = 0;
				} else {
					//setare culoarea alb
					red = 255;
					blue = 255;
					green = 255;
				}
				
				
				
				Color newColor = new Color(red, green, blue); //noua culoare
				grayscaleImage.setRGB(i, j, newColor.getRGB()); //noul pixel ia valoarea calculata
			}
		}
		
		long endTime = System.nanoTime();
		double totalTime = endTime - startTime; // calculul timpului de executie al metodei
		System.out.println("Timpul pentru conversia in binar: " + totalTime/1000000000 + "s");

		return grayscaleImage;
	}
	//getters and setters
	public ReadImage getReadImage() {
		return readImage;
	}

	public void setReadImage(ReadImage readImage) {
		this.readImage = readImage;
	}

	public BufferedImage getGrayscaleImage() {
		return grayscaleImage;
	}

	public void setGrayscaleImage(BufferedImage grayscaleImage) {
		this.grayscaleImage = grayscaleImage;
	}

	public int getWidth() {
		return grayscaleImage.getWidth();
	}
	
	public int getHeight(){
		return grayscaleImage.getHeight();
	}
	
}
