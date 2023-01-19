package packWork;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;


import javax.imageio.ImageIO;

public class WriterResults extends Thread {

	private PipedInputStream in;
	private String outputFileName;
	private GrayscaleToBinary img;
	private BufferedImage[] processedImageQuarters;
	private int i = 0;
	private BufferedImage processedImage = null;
	//constructorul cu paramaterii
	
	public WriterResults(String outputFileName, PipedInputStream in, GrayscaleToBinary image) throws IOException {
		super();
		this.outputFileName = outputFileName;
		this.in = in;
		this.img = image;
		this.i = 0;
		processedImageQuarters = new BufferedImage[4];
	}

	@Override
	public void run() {
		read(); //citirea sferturilor procesate
		processedImage = img.reconstructImage(processedImageQuarters); //formarea intregii imagini
		try {
			ImageIO.write(processedImage, "bmp", new File(outputFileName)); //scrierea in noul fisier cu numele dat de la tastatura
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Procesarea imaginii este gata! Verificati fisierul " +outputFileName);
	}
	//functie pentru citirea imaginii de la consumer prin intermediul pipe-urilor
	public void read(){
		try {
			//imi declar array-ul de bytes de dimensiunea fiecarui sfert transformat intr-un byte Array
			byte[] imageInBytes = new byte[1555254];//567054

			while (in.read(imageInBytes) != 0 && i < 4) {
				System.out.println("WriterResults: Am primit sfertul "+(i+1)+" din Consumer");
				InputStream input = new ByteArrayInputStream(imageInBytes); //transfom array-ul de byte inapoi iun imagine
				processedImageQuarters[i++] = ImageIO.read(input);
				
			}

			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public GrayscaleToBinary getImg() {
		return img;
	}

	public void setImg(GrayscaleToBinary img) {
		this.img = img;
	}

	public BufferedImage[] getProcessedImageQuarters() {
		return processedImageQuarters;
	}

	public void setProcessedImageQuarters(BufferedImage[] processedImageQuarters) {
		this.processedImageQuarters = processedImageQuarters;
	}

	public BufferedImage getProcessedImage() {
		return processedImage;
	}

	public void setProcessedImage(BufferedImage processedImage) {
		this.processedImage = processedImage;
	}

}
