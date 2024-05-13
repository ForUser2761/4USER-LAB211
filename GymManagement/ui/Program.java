package ui;

import business_layer.validate.Validate;
import ui.menu.EquipmentMenu;
import ui.menu.GymClassMenu;
import ui.menu.MemberMenu;

public class Program {
    public static void main(String[] args) {
        MemberMenu memberMenu = new MemberMenu();
        EquipmentMenu equipmentMenu = new EquipmentMenu();
        GymClassMenu gymClassMenu = new GymClassMenu();
        while (true) {
            System.out.println("========================Gym Management========================");
            System.out.println("1. Manage members.");
            System.out.println("2. Manage equipments.");
            System.out.println("3. Manage gym classes.");
            System.out.println("0. Exit.");
            int option = Validate.getInteger("Enter your choice: ",
                    "Choice must be digits", 0, 4);
            switch (option) {
                case 1:
                    memberMenu.processMenu();
                    break;
                case 2:
                    equipmentMenu.processMenu();
                    break;
                case 3:
                    gymClassMenu.processMenu();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
