package ui.implement;

import business_layer.implement.UserController;
import business_layer.validate.Validate;
import ui.IMenu;

public class UserMenu implements IMenu {

    @Override
    public void addItem(String item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 3);
    }

    @Override
    public void showMenu() {
        System.out.println("======================== User Management ========================");
        System.out.println("1. Add a User");
        System.out.println("2. Update User Information");
        System.out.println("3. Delete a User");
        System.out.println("0. Back to Main Menu");
    }

    @Override
    public void addOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOptions'");
    }

    @Override
    public void clearOptions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearOptions'");
    }

    public void run() {
        UserController userController = new UserController();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    userController.input();
                    break;
                case 2:
                    userController.update();
                    break;
                case 3:
                    userController.delete();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

}
