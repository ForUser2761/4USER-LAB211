package business_layer.service;

import java.util.List;


public interface IService<T> {
    void create(T object) throws Exception;

    T getById(String service);

    List<T> search(T t, String properties);

    void update(T objectBeUpdated, T objectInformation) throws Exception;

    void delete(T object) throws Exception;

    void printAll();
}
