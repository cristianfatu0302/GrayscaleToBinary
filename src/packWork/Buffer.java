package packWork;

import java.awt.image.BufferedImage;

public class Buffer {

	private BufferedImage[] imageQuarters;

	private int currentQuarter = 0;
	private int counter = 0;

	public Buffer() {
		imageQuarters = new BufferedImage[4];
	}

	public synchronized void put(BufferedImage quarter) {
		while (imageQuarters[3] != null) {
			try {
				wait();
				notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		imageQuarters[currentQuarter] = quarter;
		currentQuarter++;
		counter++;
		notifyAll();

	}

	public synchronized BufferedImage get(int index) throws InterruptedException {
		while (counter <= index) {
			wait();
		}

		BufferedImage item = imageQuarters[index];
		notifyAll();
		return item;
	}

	public int getCurrentQuarter() {
		return currentQuarter;
	}

	public void setCurrentQuarter(int currentQuarter) {
		this.currentQuarter = currentQuarter;
	}
}
