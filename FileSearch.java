import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class FileSearch {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: search <pattern> <file> [-i]");
            return;
        }

        boolean ignoreCase = false;
        String pattern = null;
        String filePath = null;

        if (args.length == 4 && args[3].equals("-i")) {
            ignoreCase = true;
        }

        if (args.length >= 2) {
            if (!args[0].equals("search")) {
                System.out.println("Usage: search <pattern> <file> [-i]");
                return;
            }
            pattern = args[1];
            filePath = args[2];
        } else {
            System.out.println("Usage: search <pattern> <file> [-i]");
            return;
        }

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
}
