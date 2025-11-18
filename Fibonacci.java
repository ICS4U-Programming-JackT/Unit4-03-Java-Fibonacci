import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

final class Fibonacci {

    /** Private constructor to prevent instantiation. */
    private Fibonacci() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Returns final fibonacci number after recursive calculations.
     * @return array
     * @param n Array input
     */
    public static int getFib(final int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFib(n - 1) + getFib(n - 2);
        }
    }


    /**
     * Returns a sorted version of an array.
     * @return array
     * @param lines Array input
     */
    public static int[] fibAll(final int[] lines) {
        int[] fibArray = new int[lines.length];
        // Add reversed string to each element COUNTER of array
        int counter = 0;
        for (int line : lines) {
            // Add reversed line to array, and increment counter
            fibArray[counter] = getFib(line);
            counter += 1;
        }
        return fibArray;
    }

    /**
     * Writes a sorted version of an array to a file.
     * @param lines Array input
     * @param outputFile File name to write to
     */
    public static void writeToFile(final int[] lines,
            final String outputFile) {
        // Write to file, or raise error
        try {
            // Create writer, write each line
            FileWriter writer = new FileWriter(outputFile);
            for (int line : lines) {
                writer.write(Integer.toString(line) + System.lineSeparator());
            }
            // Close writer and inform user
            writer.close();
            System.out.println("Fibonacci numbers written to " + outputFile);
        } catch (IOException e) {
            // File write error
            System.out.println("Error writing to file: " + outputFile);
        }
    }

    /**
     * Reads a file and converts its contents into an array.
     * @param inputFile Input file
     * @return array
     */
    public static int[] getLines(final String inputFile) {
        try {
            // Create file, scanner and empty array list
            File file = new File(inputFile);
            Scanner fileScanner = new Scanner(file);

            ArrayList<Integer> lines = new ArrayList<>();

            // Iterate through every line
            while (fileScanner.hasNextLine()) {
                // Read each line
                String line = fileScanner.nextLine();
                try {
                    int lineInteger = Integer.parseInt(line);
                    lines.add(lineInteger);
                } catch (NumberFormatException e) {
                    System.out.println(
                        "Error: File contained a string/empty line.");
                    fileScanner.close();
                    return new int[0];
                }
            }

            // Setup numbers array
            int[] linesArray = new int[lines.size()];
            for (int i = 0; i < linesArray.length; i++) {
                linesArray[i] = lines.get(i);
            }
            fileScanner.close();
            return linesArray;
        } catch (FileNotFoundException error) {
            // Error msg for file not found
            System.out.println("\nError: The file " + inputFile
                    + " was not found."
                    + " Please ensure it exists in the same directory.");
            return new int[0];
        }
    }

    public static void main(final String[] args) {
        // File names
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Get array, sort and write to file
        int[] array = getLines(inputFile);
        int[] sorted = fibAll(array);
        writeToFile(sorted, outputFile);
    }
}
