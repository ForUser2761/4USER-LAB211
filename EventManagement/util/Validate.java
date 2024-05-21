package util;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Validate {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get integer from user
     * @param message
     * @param error
     * @param min
     * @param max
     * @return integer
     */
    public static int getInteger(String message, String error, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();

                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.println("Bạn phải nhập trong khoảng " + min + "-" + max);
                }
            } catch (Exception e) {
                System.err.println(error);
            }
        }
    }

    /**
     * Get double from user
     * @param message
     * @param error
     * @param min
     * @param max
     * @return double
     */
    public static double getDouble(String message, String error, double min, double max) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();

                double number = Double.parseDouble(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.println("Bạn phải nhập trong khoảng " + min + "-" + max);
                }

            } catch (Exception e) {
                System.err.println(error);
            }
        }
    }

    /**
     * Get float from user
     * @param message
     * @param error
     * @param min
     * @param max
     * @return float
     */
    public static float getFloat(String message, String error, float min, float max) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();

                float number = Float.parseFloat(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.println("Bạn phải nhập trong khoảng " + min + "-" + max);
                }
            } catch (Exception e) {
                System.err.println(error);
            }

        }
    }

    /**
     * Get string from user
     * @param message
     * @param error
     * @param regex
     * @return string
     */
    public static String getString(String message, String error, String regex) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(error);
            }
        }
    }

    /**
     * Get boolean from user
     * @param message
     * @param error
     * @return true if user input y or false if user input n
     */
    public static boolean getBoolean(String message, String error) {
        String input = getString(message, error, Constant.YES_NO_REGEX);
        return input.equalsIgnoreCase("y");
    }

    /**
     * Get date from user
     * @param message
     * @param error
     * @return date in format yyyy-MM-dd
     */
    public static String getDate(String message, String error) {
        while (true) {
            // check format yyyy-MM-dd
            String regex = "^\\d{4}-\\d{2}-\\d{2}$";
            String input = getString(message, error, regex);
            // check date input exist by simpledate format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                sdf.parse(input);
                return input;
            } catch (Exception e) {
                System.err.println(error);
            }
        }
    }

    /**
     * Check user want to continue or not
     * @return true if user want to continue and false if user want to exit
     */
    public static boolean isContinue() {
        return getBoolean("Do you want to continue (Y/N)? ", "Please input Y/y or N/n");
    }
}
