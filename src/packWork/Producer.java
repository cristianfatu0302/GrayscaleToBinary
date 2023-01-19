package packWork;

import java.awt.image.BufferedImage;

public class Producer extends Thread {
	private Image img;
	private Buffer buffer;

	public Producer(Image img, Buffer buffer) {
		this.img = img;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		long startTime = System.nanoTime();
		try {
			
			for (int i = 0; i < 4; i++) {
				System.out.println("Producer: s-a citit sfertul " +  (int)(i+1));
				
				BufferedImage image = img.readImageQuarter(i, img.getFileName());
				buffer.put(image);
				buffer.setCurrentQuarter(i+1);
				
				Thread.sleep(1000);
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long finalTime = endTime - startTime;
		System.out.println("Producer: Timpul pentru citirea intregii imagini: " + finalTime/1000000000 + "s");
	}

	public Image getImage() {
		return this.img;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

}
