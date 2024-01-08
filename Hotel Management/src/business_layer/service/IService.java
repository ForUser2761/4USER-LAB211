package business_layer.service;

public interface IService<T> {
    void create(T object) throws Exception;

    T getById(String service);

    void search();

    void update(T objectBeUpdated, T objectInformation) throws Exception;

    void delete(T object) throws Exception;

    void printAll();

}
