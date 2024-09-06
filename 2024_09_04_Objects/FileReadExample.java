import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReadExample {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("2024_09_04_Objects/myTextFile.txt");
        Scanner myReader = new Scanner(myFile);
        
        while(myReader.hasNextLine()) {
            String line = myReader.nextLine();
            System.out.println(line);
        }
        // while (myReader.hasNextLine()) {
        //     String line = myReader.nextLine();
        //     System.out.println(line.length());
        //     System.out.println(line.charAt(0));
        //     if (line.length() > 0) System.out.println(line.substring(0, 2));
        //     System.out.println(line.indexOf("line"));
            
        // }
        
        myReader.close();

    }
}
