package business_layer.config;

/**
 * The CommonConfig class contains common configuration constants for the hotel management system.
 */
public class CommonConfig {
    
    /**
     * The file name constant.
     */
    public static final String FILE_NAME = "Hotel.dat";
    
    /**
     * The regex pattern for hotel IDs.
     */
    public static final String REGEX_ID = "H\\d+";

    /**
     * The regex pattern for available room numbers.
     */
    public static final String REGEX_AVALABLE_ROOM = "[1-9]\\d*";

    /**
     * The regex pattern for phone numbers.
     */
    public static final String REGEX_PHONE = "\\d{9}";

    /**
     * The regex pattern for yes/no inputs.
     */
    public static final String REGEX_YN = "[YyNn]";

    /**
     * The format string for displaying hotel information.
     */
    public static final String FORMAT_STRING_HOTEL = "%-10s | %-20s | %-10s | %-10s | %-10s | %s";
}

