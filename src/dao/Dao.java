package dao;

import model.Account;
import model.User;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T>findByID(Integer id);
    void save(T t);
    void update();
     List<T> getAll();
     void update(T t);
     void delete(T t);
}