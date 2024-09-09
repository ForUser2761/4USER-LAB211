package ui.menu;

import business_layer.validate.Validate;
import ui.GymClassUI;

public class GymClassMenu {

    public void processMenu() {
        GymClassUI gymClassUI = new GymClassUI();
        while (true) {
            showMenu();
            int choice = Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 5);
            switch (choice) {
                case 1:
                    gymClassUI.input();
                    break;
                case 2:
                    gymClassUI.update();
                    break;
                case 3:
                    gymClassUI.view();
                    break;
                case 4:
                    gymClassUI.delete();
                    break;
                case 5:
                    return;
            }
        }
    }

    private void showMenu() {
        System.out.println("========================Manage gym classes========================");
        System.out.println("1. Add a gym class.");
        System.out.println("2. View and update existing gym class information.");
        System.out.println("3. View");
        System.out.println("4. Delete");
        System.out.println("5. Back to main menu.");
    }

}
