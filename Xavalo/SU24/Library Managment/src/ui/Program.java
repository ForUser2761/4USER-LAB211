package ui;

import business_layer.implement.BookController;
import business_layer.implement.LoanController;
import business_layer.implement.UserController;
import business_layer.validate.Validate;
import ui.implement.BookMenu;
import ui.implement.LoanMenu;
import ui.implement.ReportMenu;
import ui.implement.UserMenu;

public class Program {
    public static void main(String[] args) {
        BookMenu bookMenu = new BookMenu();
        UserMenu userMenu = new UserMenu();
        LoanMenu loanMenu = new LoanMenu();
        ReportMenu reportMenu = new ReportMenu();

        while (true) {
            System.out.println("======================== Library Management ========================");
            System.out.println("1. Manage Books.");
            System.out.println("2. Manage User.");
            System.out.println("3. Manage Loans.");
            System.out.println("4. Generate Reports.");
            System.out.println("0. Exit.");
            int option = Validate.getInteger("Enter your choice: ",
                    "Choice must be digits", 0, 4);
            switch (option) {
                case 1:
                    bookMenu.run();
                    break;
                case 2:
                    userMenu.run();
                    break;
                case 3:
                    loanMenu.run();
                    break;
                case 4:
                    reportMenu.run();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
