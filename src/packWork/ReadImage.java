package packWork;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


import javax.imageio.ImageIO;

public class ReadImage extends Image implements ImageInterface {
	
	BufferedImage img;
	//constructorul fara parametrii
	public ReadImage() {
		super();
	}
	//functie utilizata pentru citirea sfertului de imagine
	//primeste ca parametrii sfertul pe care vrem sa il citim si numele pozei din care citim
	public BufferedImage readImageQuarter(int quarter, String fileName){
		
		try{
			File inputFile = new File(fileName);
			image = ImageIO.read(inputFile);
			
			int width = image.getWidth();
			int height = image.getHeight();
			
			//citirea sferului de imagine
			if(quarter == 0){
				image = image.getSubimage(0,  0, width/2, height/2);
			}else if(quarter == 1){
				image = image.getSubimage(width/2, 0, width/2, height/2);
			}else if(quarter == 2){
				image = image.getSubimage(0,  height/2, width/2, height/2);
			}else if(quarter == 3){
				image = image.getSubimage(width/2, height/2, width/2,  height/2);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
			
			return image;
	}
		
	//functie facuta pentru citirea fisierului de intrare
	// returneaza numele fisierului
	public String ReadFile(){
		
		long startTime = System.nanoTime();
		
		String inputFile = null; // numele fisierului de intrare
		try{
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(input);
			System.out.println("Numele pozei care sa fie prelucrata(.bmp):");
			
			inputFile = (bufferedReader.readLine()).toString();
			while(!inputFile.endsWith(".bmp")){
				System.out.println("Poza trebuie sa fie .bmp!!!");
				inputFile = (bufferedReader.readLine()).toString();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println("Timp pentru citirea imaginii : " + totalTime/1000000000  + " s");
		
		return inputFile;
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
}
