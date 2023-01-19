package packWork;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class Consumer extends Thread {

	private GrayscaleToBinary gs;
	private BufferedImage[] imageQuarters;
	private Buffer buffer;
	private PipedOutputStream oos;

	public Consumer(GrayscaleToBinary gs, Buffer buffer, PipedOutputStream out) throws IOException {
		this.gs = gs;
		this.buffer = buffer;
		this.imageQuarters = new BufferedImage[4];
		this.oos = out;
	}

	@Override
	public void run() {
		try {
			long startTime = System.nanoTime();
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				imageQuarters[i] = buffer.get(i);
				imageQuarters[i] = gs.processImage(imageQuarters[i]); //procesarea fiecarui sfert de imagine
				System.out.println("Consumer: s-a procesat sfertul  " + (int) (i + 1));
			}
			long endTime = System.nanoTime();
			long finalTime = endTime - startTime;
			System.out.println("Procesarea intregii imagini in Consumer: " + finalTime / 1000000000 + "s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			writeToWriter(); // transmiterea sferturilor catre writerResults
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//functie pentru a transmite catre writerResults prin intermediul piep-urilor
	public void writeToWriter() throws IOException {

		long startTime = System.nanoTime();
		
		for (int i = 0; i < 4; i++) {
			//transform sfertul de imagine in byte array si apoi il transmit mai departe
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(imageQuarters[i], "bmp", byteArrayOutputStream);
			byteArrayOutputStream.flush();
			byte[] imageInBytes = byteArrayOutputStream.toByteArray();
			System.out.println("Consumer: S-a transmis sfertul " + (i + 1) + " catre WriterResults");
			
			oos.write(imageInBytes);
			oos.flush();
			byteArrayOutputStream.close();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		oos.close();
		long endTime = System.nanoTime();
		long finalTime = endTime - startTime;
		System.out.println("Consumer: Timpul de scriere catre WriterResults: " + finalTime / 1000000000 + "s");
	}
	//getters and setters
	public GrayscaleToBinary getGs() {
		return gs;
	}

	public void setGs(GrayscaleToBinary gs) {
		this.gs = gs;
	}

	public BufferedImage[] getImageQuarters() {
		return imageQuarters;
	}

	public void setImageQuarters(BufferedImage[] imageQuarters) {
		this.imageQuarters = imageQuarters;
	}

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

}
