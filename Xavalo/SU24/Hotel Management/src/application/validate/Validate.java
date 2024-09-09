package application.validate;

import java.util.Scanner;

public class Validate {

    private static Scanner scanner = new Scanner(System.in);

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

    public static String getString(String message, String error, String regex) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(error);
            }
        }
    }
}
