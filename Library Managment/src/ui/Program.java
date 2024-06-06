package ui;

import business_layer.validate.Validate;
import ui.implement.BookMenu;

public class Program {
    public static void main(String[] args) {
        BookMenu bookMenu = new BookMenu();
        while (true) {
            System.out.println("======================== Library Management ========================");
            System.out.println("1. Manage Books.");
            System.out.println("2. Manage User.");
            System.out.println("3. Manage Loans.");
            System.out.println("4. Report");
            System.out.println("0. Exit.");
            int option = Validate.getInteger("Enter your choice: ",
                    "Choice must be digits", 0, 4);
            switch (option) {
                case 1:
                    bookMenu.run();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
