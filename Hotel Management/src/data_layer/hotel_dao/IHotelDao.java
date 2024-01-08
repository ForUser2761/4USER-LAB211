package data_layer.hotel_dao;

public interface IHotelDao<Hotel> {
    void loadDataFromFile() throws Exception ; 

    void writeToFile() throws Exception;
}
