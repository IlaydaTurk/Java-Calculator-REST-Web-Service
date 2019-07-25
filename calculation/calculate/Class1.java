package calculation.calculate;

import java.util.Random;
public class Class1 {

	public int getTmp() throws InterruptedException {
		Random rand = new Random();

		int  n = rand.nextInt(60000) + 1000;
		Thread.sleep(n);
		return 10;
	}
}
