package lru;

public class LRU extends ReplacementAlgorithm {
	
	private int[] referenceString;
	private int pageSize;
	
	public LRU(int[] referenceString, int pageSize) {
		this.pageSize = pageSize;
		this.referenceString = referenceString;
	}

	@Override
	public int[] insert() {
		return null;
	}
}
