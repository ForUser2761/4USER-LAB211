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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showMenu'");
    }

    
}
