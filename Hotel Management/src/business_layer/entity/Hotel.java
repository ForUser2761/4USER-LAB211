package business_layer.entity;

import application.validate.Validate;
import business_layer.config.CommonConfig;

public class Hotel {

    private String id;
    private String name;
    private int room_available;
    private String address;
    private String phone;
    private int rating;

    // Default constructor
    public Hotel() {
    }

    // Constructor
    public Hotel(String id, String name, int room_available, String address, String phone, int rating) {
        this.id = id;
        this.name = name;
        this.room_available = room_available;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom_available() {
        return room_available;
    }

    public void setRoom_available(int room_available) {
        this.room_available = room_available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void input() {
        // Validate id
        String id = Validate.getString("Enter hotel ID: ", "Invalid ID. The correct format is H followed by numbers.",
                CommonConfig.REGEX_ID);
        setId(id);

        // Validate name
        String name = Validate.getString("Enter hotel name: ", "Invalid name. Please enter a valid name.", ".*");
        setName(name);

        // Validate room_available
        int roomAvailable = Validate.getInteger("Enter number of available rooms: ",
                "Invalid number. Please enter a positive integer.", 1, Integer.MAX_VALUE);
        setRoom_available(roomAvailable);

        // Validate address
        String address = Validate.getString("Enter hotel address: ", "Invalid address. Please enter a valid address.",
                ".*");
        setAddress(address);

        // Validate phone
        String phone = Validate.getString("Enter hotel phone number: ",
                "Invalid phone number. Please enter a 9-digit number.", CommonConfig.REGEX_PHONE);
        setPhone(phone);

        // Validate rating
        int rating = Validate.getInteger("Enter hotel rating: ",
                "Invalid rating. Please enter a number between 1 and 5.", 1, 5);
        setRating(rating);
    }

    @Override
    public String toString() {
        return String.format(CommonConfig.FORMAT_STRING_HOTEL, id, name, room_available,
                phone, rating, address);
    }

}
