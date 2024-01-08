package application.ui;

import application.ui.implement.Menu;
import application.validate.Validate;
import business_layer.config.CommonConfig;
import business_layer.entity.Hotel;
import business_layer.service.IService;

/**
 * The HotelUI class represents the user interface for managing hotels.
 * It provides methods for adding, checking, displaying, deleting, and updating hotels.
 */
/**
 * The HotelUI class represents the user interface for managing hotels.
 * It provides methods for adding, checking, displaying, deleting, and updating
 * hotels.
 */

public class HotelUI {

    IService<Hotel> service;

    /**
     * Constructs a HotelUI object with the specified service.
     * 
     * @param service the service used for hotel management
     */
    public HotelUI(IService<Hotel> service) {
        this.service = service;
    }

    /**
     * Adds a new hotel to the system.
     * 
     * @throws Exception if an error occurs during the process
     */
    public void addingNewHotel() {
        try {
            Hotel hotel = new Hotel();
            hotel.input();
            // add hotel to list
            service.create(hotel);
            System.out.println("Adding successful !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Checks if a hotel exists in the system.
     */
    public void checkingExistHotel() {

    }

    /**
     * Displays all hotels in the system.
     */
    public void displayAllHotels() {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(CommonConfig.FORMAT_STRING_HOTEL + "\n",
                "Hotel_id", "Hotel_name", "Available", "Phone", "Rating", "Address");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------");
        service.printAll();
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Deletes a hotel from the system.
     */
    public void deleteHotel() {
        String id = Validate.getString("Enter hotel ID: ",
                "Invalid ID. The correct format is H followed by numbers.",
                CommonConfig.REGEX_ID);
        Hotel hotel = new Hotel();
        hotel.setId(id);
        try {
            // Confirm message
            if (Menu.confirm("delete this hotel")) {
                service.delete(hotel);
                System.out.println("Remove successfull !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Updates the information of a hotel in the system.
     */
    public void updateHotel() {
        String id = Validate.getString("Enter the hotel's ID you want to modify :",
                "Invalid ID. The correct format is H followed by numbers.",
                CommonConfig.REGEX_ID);
        try {
            // Check if hotel exists
            Hotel hotelToUpdate = service.getById(id);
            if (hotelToUpdate != null) {
                // Get new information from user
                Hotel newHotelInfo = new Hotel();
                newHotelInfo.input();

                service.update(hotelToUpdate, newHotelInfo);

                // Print the updated hotel information
                System.out.println("Updated hotel information:");
                System.out.println(hotelToUpdate);
            } else {
                System.out.println("Hotel does not exist.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
