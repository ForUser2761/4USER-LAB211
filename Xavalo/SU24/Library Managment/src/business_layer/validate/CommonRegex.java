package business_layer.validate;

public class CommonRegex {
    // Regex for any string
    public static final String STRING_REGEX = ".+|\\s+";

    // Regex for validating y or n
    public static final String YES_NO_REGEX = "[yYnN]";

    public static final String BOOK_TITLE_REGEX = "^[a-zA-Z0-9\\s]+$";
    public static final String BOOK_AUTHOR_REGEX = "^[a-zA-Z\\s]+$";
    public static final String DATE_REGEX = "^(0[1-9]|1[0-2])\\/(0[1-9]|[12][0-9]|3[01])\\/(19|20)\\d{2}$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,3}[- ]?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
}
