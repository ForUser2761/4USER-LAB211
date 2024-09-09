
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextNormalizer {

    public static void main(String[] args) {

        try {
            // Create a sample input file
            writeToFile("input.txt", """
                            as you can see, Detecting whether a string is normalized can be quite efficient. A lot of the cost of normalizing in the second row
                            is for the initialization of buffers. The cost of which is amortized when one is processing larger strings.
                            As it turns out, These buffers are rarely needed, so we may change the implementation at some point to speed up the common case for small strings even further""");

            // Read the input file
            String content = new String(Files.readAllBytes(Paths.get("input.txt")));

            // Normalize the content
            String normalizedContent = normalizeText(content);

            // Write the normalized content to the output file
            writeToFile("output.txt", normalizedContent);
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }

    private static String normalizeText(String content) {
        // Split the content into lines
        String[] lines = content.split("\\r?\\n");
        StringBuilder normalizedContent = new StringBuilder();
    
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                // Preserve empty lines
                normalizedContent.append("\n");
            } else {
                // Normalize non-empty lines
                line = line.replaceAll("\\s+", " ");
                line = line.replaceAll("\\s*([,.:])\\s*", "$1 ");
                line = line.replaceAll("\\s*\"\\s*", "\"");
                line = line.replaceAll("([,.:])(\\S)", "$1 $2");
    
                // Capitalize first word of each sentence and first line
                String[] sentences = line.split("(?<=[.!?])\\s+");
                StringBuilder normalizedLine = new StringBuilder();
    
                boolean isFirstSentence = true;
                for (String sentence : sentences) {
                    sentence = sentence.trim();
                    if (!sentence.isEmpty()) {
                        if (isFirstSentence) {
                            normalizedLine.append(Character.toUpperCase(sentence.charAt(0)))
                                    .append(sentence.substring(1).toLowerCase()).append(" ");
                            isFirstSentence = false;
                        } else {
                            normalizedLine.append(Character.toUpperCase(sentence.charAt(0)))
                                    .append(sentence.substring(1).toLowerCase()).append(" ");
                        }
                    }
                }
    
                // Add normalized line to the result
                normalizedContent.append(normalizedLine.toString().trim()).append("\n");
            }
        }
    
        // Remove trailing new line and ensure there's a dot at the end of the last line
        String result = normalizedContent.toString().trim();
        if (!result.endsWith(".")) {
            result += ".";
        }
    
        return result;
    }
    

    private static void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }
}
