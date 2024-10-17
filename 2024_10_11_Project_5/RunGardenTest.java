import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class RunGardenTest {

    private String readExpectedOutput(String filename) throws FileNotFoundException {
        File myFile = new File(filename);
        Scanner myReader = new Scanner(myFile);
        String content = "";
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            content += line + "\n";
        }
        myReader.close();

        return content;

    }

    @Test
    void testAddTooMuch() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/addTooMuch.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-addTooMuch.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testBadCommands() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/badcommands.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-badcommands.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testOneBananaGrowOnce() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/onebanana_growonce.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-onebanana_growonce.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testOneOfEverythingGrowOnce() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/oneofeverything_growonce.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-oneofeverything_growonce.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testOneRoseGrowOnce() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/onerose_growonce.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-onerose_growonce.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testOneYamGrowOnce() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/oneyam_growonce.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-oneyam_growonce.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testPlantCool() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/plantcool.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-plantcool.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

    @Test
    void testThreeTreesGrowThree() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        String studentOutput;

        System.setOut(new PrintStream(outputStreamCaptor));

        String[] names = { "2024_10_11_Project_5/TestCases/threetrees_growthree.in" };
        try { RunGarden.main(names); } catch (Exception e) { System.out.println("This test threw some error: " + e);}
        String expectedOutput = readExpectedOutput("2024_10_11_Project_5/TestCases/pa5-threetrees_growthree.out");
        studentOutput = outputStreamCaptor.toString().trim();
        assertEquals(expectedOutput.trim(), studentOutput.trim());
        System.setOut(standardOut);
        System.out.println(studentOutput);
    }

}