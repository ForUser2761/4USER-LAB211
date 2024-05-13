package ui.menu;

import business_layer.EquipmentUI;
import business_layer.validate.Validate;

public class EquipmentMenu {

    public void processMenu() {
        EquipmentUI equipUI = new EquipmentUI();
        while (true) {
            showMenu();
            int choice = Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 5);
            switch (choice) {
                case 1:
                    equipUI.input();
                    break;
                case 2:
                    equipUI.sortAscendingByName();
                    break;
                case 3:
                    equipUI.viewAndUpdate();
                    break;
                case 4:
                    equipUI.delete();
                    break;
                case 5:
                    return;
            }
        }
    }

    private void showMenu() {
        System.out.println("========================Manage equipments========================");
        System.out.println("1. Add an equipment.");
        System.out.println("2. Sort and print the list of equipments ascending by name.");
        System.out.println("3. View and update existing equipment information.");
        System.out.println("4. Delete an equipment.");
        System.out.println("5. Back to main menu.");
    }

    
}
