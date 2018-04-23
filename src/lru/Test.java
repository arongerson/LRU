package lru;

public class Test {
	
	private static int referenceStringSize;
	private static int numberOfPageFrames;
	
	public static void main(String[] args) {
		boolean validCommandLineArgs = findCommandLineArgs(args);
		if (validCommandLineArgs) {
			PageGenerator pageGenerator = new PageGenerator(referenceStringSize);
			int[] referenceString = pageGenerator.getReferenceString();
			ReplacementAlgorithm replacementAlgorithm = new LRU(referenceString, numberOfPageFrames);
			int[] output = replacementAlgorithm.insert();
			System.out.println("Number of page replacements: " + output[0] + ", Number of page faults: " + output[1]); 
		} else {
			System.out.println("Invalid command line arguments"); 
		}
	}

	private static boolean findCommandLineArgs(String[] args) {
		if (args.length != 2) {
			return false;
		}
		try {
			referenceStringSize = Integer.parseInt(args[0]);
			numberOfPageFrames = Integer.parseInt(args[1]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
