package lru;

public class LRU extends ReplacementAlgorithm {

	private int[] referenceString;
	private int pageSize;
	private int numberOfPageFaults;
	private Frame[] frames;

	public LRU(int[] referenceString, int pageSize) {
		this.pageSize = pageSize;
		this.referenceString = referenceString;
		initFrames();
	}

	private void initFrames() {
		frames = new Frame[pageSize];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame(-1, -1);
		}
	}

	@Override
	public int[] insert() {
		int numberOfPageReplacements = 0;
		for (int i = 0; i < referenceString.length; i++) {
			int pageIndex = findPageIndex(referenceString[i]);
			if (pageExists(pageIndex)) {
				frames[pageIndex].count = i;
				System.out.println(referenceString[i] + " exists, not replaced");
			} else {
				if (isFrameMemoryFull()) {
					int frameIndexToReplace = findPageIndexToRemove();
					System.out.println(referenceString[i] + " replacing " + frames[frameIndexToReplace].pageNumber);
					frames[frameIndexToReplace].pageNumber = referenceString[i];
					frames[frameIndexToReplace].count = i;
					numberOfPageReplacements++;
				} else {
					frames[numberOfPageFaults].pageNumber = referenceString[i];
					frames[numberOfPageFaults].count = i;
					System.out.println("Memory available, adding " + referenceString[i]);
				}
				numberOfPageFaults++;
			}
		}
		return new int[] {numberOfPageReplacements, numberOfPageFaults};
	}
	
	private boolean pageExists(int pageIndex) {
		return pageIndex != -1;
	}

	private int findPageIndex(int pageNumber) {
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].pageNumber == pageNumber) {
				return i;
			}
		}
		return -1;
	}

	private boolean isFrameMemoryFull() {
		return numberOfPageFaults >= frames.length;
	}

	private int findPageIndexToRemove() {
		int min = frames[0].count;
		int index = 0;
		for (int i = 1; i < frames.length; i++) {
			if (frames[i].count < min) {
				min = frames[i].count;
				index = i;
			}
		}
		return index;
	}
}
