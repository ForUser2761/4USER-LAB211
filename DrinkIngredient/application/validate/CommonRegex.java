package application.validate;

public class CommonRegex {
    // Regex for validating ingredient code, it must start with I and after that is digits
    public static final String INGREDIENT_CODE_REGEX = "^I\\d+$";

    //Regex for any string
    public static final String STRING_REGEX = ".+";

    //Regex for validating y or n
    public static final String YES_NO_REGEX = "[yYnN]";
}
