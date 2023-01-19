package packWork;

import java.awt.image.BufferedImage;


public abstract class Image {
	//clasa abstracta pentru imagine ce are ca parametrii numele fisierului si un BufferedImage
	protected String fileName;
	protected BufferedImage image;
	
	//constructorul fara parametrii
	public Image(){
		
	}
	
	public Image(String fileName){
		this.fileName = fileName;
	}
	
	public abstract BufferedImage readImageQuarter(int quarter, String fileName);//functie pentru citirea sfertului de imagine
	
	//getters si setters
	public BufferedImage getImage() {
		return image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
