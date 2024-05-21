package view;

import controller.EventController;

public class Program {
    public static void main(String[] args) {
        EventController eventController = new EventController();
        while (true) {
            displayMenu();
            int choice = util.Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 7);                        
            switch(choice) {
                case 1:
                    // Create a new event
                    eventController.inputEvent();
                    break;
                case 2:
                    // Check if an event exists
                    eventController.searchEvent();
                    break;
                case 3:
                    // Search for event information by location
                    eventController.searchEventByLocation();
                    break;
                case 4:
                    // Update event
                    int updateChoice = util.Validate.getInteger("Enter your choice: ", "Choice must be digits", 1, 2);
                    switch(updateChoice) {
                        case 1:
                            // Update event details
                            eventController.updateEvent();
                            break;
                        case 2:
                            // Delete event
                            eventController.deleteEvent();
                            break;
                    }
                    break;
                case 5:
                    // Print the list of events from the file
                    eventController.printEventList();
                    break;
                case 6:
                    System.exit(0);
            
            }
        }
    }

    private static void displayMenu() {
        System.out.println("========================Event Management========================");
        System.out.println("1. Create a new event.");
        System.out.println("2. Check if an event exists.");
        System.out.println("3. Search for event information by location.");
        System.out.println("4. Update event:");
        System.out.println("   4.1. Update event details.");
        System.out.println("   4.2. Delete event.");
        System.out.println("5. Print the list of events from the file.");
        System.out.println("6. Others - Quit.");          
    }
}
