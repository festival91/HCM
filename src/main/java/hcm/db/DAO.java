package hcm.db;

import java.util.List;

public interface DAO<T> {

    T getObjectById(String objectID);

    List<T> getAllObjects();

    int save(T object);

    int update(T object);

    int delete(T object);

    int insert(T object);

}
