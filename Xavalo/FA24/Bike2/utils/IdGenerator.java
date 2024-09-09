package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
     private static int counter = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    public static String generateId(String prefix) {
        LocalDateTime now = LocalDateTime.now();
        String timePrefix = now.format(formatter);
        return String.format("%s%s%03d",prefix, timePrefix, ++counter);
    }
}
