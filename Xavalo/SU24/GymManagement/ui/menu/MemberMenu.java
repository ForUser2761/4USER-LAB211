package ui.menu;

import business_layer.MemberUI;
import business_layer.validate.Validate;

public class MemberMenu {

    public void processMenu() {
        MemberUI memberUI = new MemberUI();
        while (true) {
            showMenu();
            int choice = Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 5);
            switch (choice) {
                case 1:
                    memberUI.input();
                    break;
                case 2:
                    memberUI.sortAscendingByName();
                    break;
                case 3:
                    memberUI.viewAndUpdate();
                    break;
                case 4:
                    memberUI.deleteMember();
                    break;
                case 5:
                    return;
            }
        }
    }

    private void showMenu() {
        System.out.println("========================Manage members========================");
        System.out.println("1. Add a member.");
        System.out.println("2. Sort and print the list of members ascending by name.");
        System.out.println("3. View and update existing member information.");
        System.out.println("4. Delete a member.");
        System.out.println("5. Back to main menu.");
    }
    
}
