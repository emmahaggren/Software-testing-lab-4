import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class FileSearch {
    public static void main(String[] args) {
        System.out.println("Usage: search <pattern> <file> [-i]");
        boolean running = true;

        Scanner scanner = new Scanner(System.in);
        while (running) {
            String commandLine = scanner.nextLine();
            String[] command = commandLine.split(" ");
            if (command.length > 0 && !command[0].equals("search")) {
                if (command[0].equals("exit")) {
                    System.out.println("Goodbye");
                    break;
                }
                System.out.println("Usage: search <pattern> <file> [-i]");
                continue;
            }
            if (command.length < 3) {
                System.out.println("Usage: search <pattern> <file> [-i]");
                continue;
            }

            boolean ignoreCase = false;
            String pattern = null;
            String filePath = null;

            if (command.length == 4 && command[3].equals("-i")) {
                ignoreCase = true;
            }

            pattern = command[1];
            filePath = command[2];

            try {
                // Read the file
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                // Compile regex pattern
                Pattern regexPattern = ignoreCase
                        ? Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
                        : Pattern.compile(pattern);

                // Search for the pattern in each line
                for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                    String line = lines.get(lineNumber);
                    Matcher matcher = regexPattern.matcher(line);
                    if (matcher.find()) {
                        System.out.println("Line " + (lineNumber + 1) + ": " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
