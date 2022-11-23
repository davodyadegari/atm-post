package dao;

import model.Account;
import model.User;

import java.util.List;

public interface Dao<T> {
    T save(T t);
    void update();
     List<T> read();
}