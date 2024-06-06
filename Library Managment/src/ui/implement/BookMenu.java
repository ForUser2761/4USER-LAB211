package ui.implement;

import business_layer.implement.BookController;
import business_layer.validate.Validate;
import ui.IMenu;

public class BookMenu implements IMenu {

    @Override
    public void addItem(String item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 4);
    }

    @Override
    public void showMenu() {
        System.out.println("======================== Book Management ========================");
        System.out.println("1. Add a Book");
        System.out.println("2. Update Book Information");
        System.out.println("3. Delete a Book");
        System.out.println("4. Show All Books");
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
        BookController bookController = new BookController();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    bookController.input();
                    break;
                case 2:
                    break;
                case 3:
                    bookController.delete();
                    break;
                case 4:
                    bookController.showAll();
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
