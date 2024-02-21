package application.validate;

public class CommonRegex {
    // Regex for validating ingredient code, it must start with I and after that is
    // digits
    public static final String INGREDIENT_CODE_REGEX = "^I\\d+$";

    // Regex for any string
    public static final String STRING_REGEX = ".+";

    // Regex for validating y or n
    public static final String YES_NO_REGEX = "[yYnN]";

    /**
     * Regular expression pattern for ingredient code validation.
     * Allows blank or a string starting with 'I' followed by zero or more digits.
     */
    public static final String INGREDIENT_CODE_REGEX_BLANK = "^(I\\d*|)$";

    /**
     * Regular expression pattern for ingredient name validation.
     * Allows blank or any string.
     */
    public static final String INGREDIENT_NAME_REGEX_BLANK = "^(.*|)$";

    /**
     * Regular expression pattern for ingredient quantity validation.
     * Allows blank or a string of digits.
     */
    public static final String INGREDIENT_QUANTITY_REGEX_BLANK = "^(\\d*|)$";

    /**
     * Regular expression pattern for ingredient price validation.
     * Allows blank or a string of digits.
     */
    public static final String INGREDIENT_PRICE_REGEX_BLANK = "^(\\d*|)$";

    /**
     * Regular expression for drink codes.
     * The drink code should start with 'D' followed by three digits.
     */
    public static final String DRINK_CODE_REGEX = "D\\d{3}";

    /**
     * Regular expression pattern for drink code validation.
     * Allows blank or a string starting with 'D' followed by three digits.
     */
    public static final String DRINK_CODE_REGEX_BLANK = "^(D\\d{3}|)$";

    /**
     * Regular expression pattern for drink name validation.
     * Allows blank or any string.
     */
    public static final String DRINK_NAME_REGEX_BLANK = "^(.*|)$";

    /**
     * Regular expression pattern for drink price validation.
     * Allows blank or a string of digits.
     */
    public static final String DRINK_PRICE_REGEX_BLANK = "^(\\d*|)$";

    /**
     * Regular expression pattern for positive integer validation.
     * Allows blank or a string of digits.
     */
    public static final String POSITIVE_INTEGER_REGEX = "^(\\d+|)$";
}
