package ui;

import business_layer.validate.Validate;
import ui.menu.MemberMenu;

public class Program {
    public static void main(String[] args) {
        MemberMenu memberMenu = new MemberMenu();
        while (true) {
            System.out.println("========================Gym Management========================");
            System.out.println("1. Manage members.");
            System.out.println("0. Exit.");
            int option = Validate.getInteger("Enter your choice: ",
                 "Choice must be digits", 0, 4);
            switch (option) {
                case 1:
                    memberMenu.processMenu();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
