package business_layer.validate;

public class CommonRegex {
    // Regex for any string
    public static final String STRING_REGEX = ".+";

    // Regex for validating y or n
    public static final String YES_NO_REGEX = "[yYnN]";

    public static final String BOOK_TITLE_REGEX = "^[a-zA-Z0-9\\s]+$";
    public static final String BOOK_AUTHOR_REGEX = "^[a-zA-Z\\s]+$";
}
