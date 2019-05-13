package Beispiel02.Functionalities.Dao;

import java.util.List;

public interface Dao<T> {

    T readForIdentity(long id);
    List<T> readAll();
    boolean create(T entity);
    boolean delete(long id);

}
