package dao;

import model.Account;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class UserDao implements Dao<User> {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm", "postgres", "2523249");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User t) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name ,family,age,national_code,birthday) values (?,?,?,?,?)");
       preparedStatement.setString(1,t.getName());
       preparedStatement.setString(2,t.getFamily());
       preparedStatement.setInt(3,t.getAge());
       preparedStatement.setString(4,t.getNationalCode());
       preparedStatement.setDate(5, Date.valueOf(t.getBirthday()));
           preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void update() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set national_code=? where name =?");
            preparedStatement.setString(1,"4444455555");
            preparedStatement.setString(2,"davod");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> read() {
        return null;
    }



}