package application.ui;

public interface IMenu {
    
    /**
     * add a menu item --> add text to menu
     * @param item
     */
    void addItem(String item);

    /**
     * get user choice (user input their choice)
     * @return  user choice
     */
    int getChoice();

    /**
     * show menu for user choice
     */
    void showMenu();

}
