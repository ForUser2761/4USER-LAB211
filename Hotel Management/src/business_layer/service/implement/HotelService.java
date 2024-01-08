package business_layer.service.implement;

import java.util.List;
import business_layer.entity.Hotel;
import business_layer.service.IService;
import data_layer.hotel_dao.HotelDAO;

/**
 * The HotelService class implements the IService interface and provides
 * functionality for managing hotels.
 */
public class HotelService implements IService<Hotel> {
    HotelDAO hotelDAO;

    /**
     * Constructs a new HotelService object.
     */
    public HotelService() {
        this.hotelDAO = new HotelDAO();
    }

    /**
     * Creates a new hotel.
     *
     * @param hotel The hotel to be created.
     * @throws Exception If the hotel already exists.
     */
    @Override
    public void create(Hotel hotel) throws Exception {
        // checking hotel exist
        Hotel hotelInList = getById(hotel.getId());
        // add hotel to list
        if (hotelInList == null) {
            hotelDAO.addHotel(hotel);
        }else {
            throw new Exception("Hotel was exist !!");
        }
    }

    /**
     * Retrieves a hotel by its ID.
     *
     * @param id The ID of the hotel to retrieve.
     * @return The hotel with the specified ID, or null if not found.
     */
    @Override
    public Hotel getById(String id) {
        List<Hotel> hotels = hotelDAO.getHotelList(); // Get data from HotelDAO and assign it to ArrayList
        for (Hotel hotel : hotels) {
            if (hotel.getId().equalsIgnoreCase(id)) {
                return hotel; // Return the hotel with matching id
            }
        }
        return null; // Return null if no hotel with matching id is found
    }

    /**
     * Searches for hotels.
     * This method is not implemented.
     */
    @Override
    public void search() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    /**
     * Updates the information of a hotel.
     *
     * @param objBeUpdated The hotel to be updated.
     * @param hotelInformation The updated information of the hotel.
     * @throws Exception If an error occurs during the update process.
     */
    @Override
    public void update(Hotel objBeUpdated, Hotel hotelInformation) throws Exception {
        // Update hotel information
        if (!hotelInformation.getName().isEmpty()) {
            objBeUpdated.setName(hotelInformation.getName());
        }
        if (!Integer.toString(hotelInformation.getRoom_available()).isEmpty()) {
            objBeUpdated.setRoom_available(hotelInformation.getRoom_available());
        }
        if (!hotelInformation.getPhone().isEmpty()) {
            objBeUpdated.setPhone(hotelInformation.getPhone());
        }
        if (!Integer.toString(hotelInformation.getRating()).isEmpty()) {
            objBeUpdated.setRating(hotelInformation.getRating());
        }
        if (!hotelInformation.getAddress().isEmpty()) {
            objBeUpdated.setAddress(hotelInformation.getAddress());
        }

        //write to file
        hotelDAO.writeToFile();
    }

    /**
     * Deletes a hotel.
     *
     * @param hotel The hotel to be deleted.
     * @throws Exception If the hotel is not found.
     */
    @Override
    public void delete(Hotel hotel) throws Exception {
        // checking hotel exist
        Hotel hotelInList = getById(hotel.getId());
        // remove hotel in list
        if (hotelInList == null) {
            throw new Exception("Not Found !!");
        }else {
            hotelDAO.delete(hotel);
        }
    }

    /**
     * Prints all hotels.
     */
    @Override
    public void printAll() {
        hotelDAO.loadDataFromFile();
        List<Hotel> hotels = hotelDAO.getHotelList(); // Get data from HotelDAO and assign it to ArrayList
        for (Hotel hotel : hotels) {
            System.out.println(hotel); // Print each hotel in the ArrayList
        }
    }

}
