package data_layer;

public interface IGenericDAO<T> {
    public static final String BOOK_FILE = "book.txt";
    public static final String USER_FILE = "user.txt";

    void loadDataFromFile() throws Exception ; 

    void writeToFile() throws Exception;

    void insert(T t) throws Exception;

    void clear();
}
