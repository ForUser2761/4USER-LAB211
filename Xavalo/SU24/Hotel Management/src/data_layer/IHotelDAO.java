package data_layer;

public interface IHotelDAO<T> {
    void loadDataFromFile() throws Exception ; 

    void writeToFile() throws Exception;

}
