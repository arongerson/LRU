package lru;

import java.util.Random;

public class PageGenerator {
	
	private int numberOfFrames;
	
	public PageGenerator(int numberOfFrames) { 
		this.numberOfFrames = numberOfFrames;
	}
	
	public int[] getReferenceString() {
		int[] referenceString = new int[numberOfFrames];
		Random random = new Random();
		for (int i = 0; i < numberOfFrames; i++) {
			referenceString[i] = random.nextInt(10);
		}
		return referenceString;
	}
}
