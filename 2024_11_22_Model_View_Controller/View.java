import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();
        if(args.length != 1) {
            System.out.println("Usage: java View <file>");
            System.exit(1);
        }
        File file = new File(args[0]);
        Scanner reader = new Scanner(file);
        
        while (reader.hasNext()) {
            controller.parseLine(reader.nextLine());
        }
        
        System.out.println(controller);
        reader.close();
        
    }
}
