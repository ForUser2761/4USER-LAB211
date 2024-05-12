package business_layer.validate;

public class Constant {

    // Regex for any string
    public static final String STRING_REGEX = ".*";

    // Regex for validating y or n
    public static final String YES_NO_REGEX = "[yYnN]";

    /**
     * Regex for validating a member ID. The Id must be format Mxxxxxx where x is a digit.
     */
    public static final String MEMBER_ID = "(M\\d{1,6}|\\s)";

    /**
     * Regex for validating an email address.
     */
    public static final String EMAIL_REGEX = "^(?:[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,24}|\\s*)$";

                ;

    /**
     * Regex for validating a phone number. The phone number must be 10 digits.
     */
    public static final String PHONE_REGEX = "(\\d{10}|\\s*)";

    /**
     * The file name to store members' information.
     */
    public static final String MEMBER_FILE_NAME = "members.txt";
}
