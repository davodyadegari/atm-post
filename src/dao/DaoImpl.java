package dao;

import java.sql.Connection;

public abstract class DaoImpl<T>implements Dao {
    protected Connection connection=DbConnector.getConnection();
}