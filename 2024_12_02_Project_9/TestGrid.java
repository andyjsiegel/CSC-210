// import java.io.File;
// import java.util.ArrayList;
// import java.util.Scanner;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import java.io.FileNotFoundException;

// class TestGrid {

// 	@Test
// 	void test() throws FileNotFoundException {
// 		File wordsFile = new File("/Users/andysiegel/Library/CloudStorage/GoogleDrive-andyjsiegel1@gmail.com/My Drive/University/Semester 3/CSC 210/words.txt");
// 		Scanner reader = new Scanner(wordsFile);
		
// 		String[] wAndH = reader.nextLine().split(" ");
// 		int width = Integer.valueOf(wAndH[0]);
// 		int height = Integer.valueOf(wAndH[1]);
		
// 		ArrayList<String> words = FindWords.readFile(reader);
// 		reader.close();
		
// 		File gridFile = new File("/Users/andysiegel/Library/CloudStorage/GoogleDrive-andyjsiegel1@gmail.com/My Drive/University/Semester 3/CSC 210/output_words.txt");
// 		Scanner gridReader = new Scanner(gridFile);
		
// 		ArrayList<String> grid = FindWords.readFile(gridReader);
// 		gridReader.close();
		
// 		Assertions.assertTrue(FindWords.findWords(words, grid));
// 		Assertions.assertTrue(FindWords.dimensionsMatch(width, height, grid));
// 	}

// }