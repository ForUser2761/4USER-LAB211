package data_layer;

public interface IGenericDAO<T> {
    public static final String INGREDIENT_FILE_NAME = "Ingredients.dat";

    public static final String DRINK_FILE_NAME = "Drinks.dat";
    
    void loadDataFromFile() throws Exception ; 

    void writeToFile() throws Exception;

    void insert(T t) throws Exception;

    void clear();
}
