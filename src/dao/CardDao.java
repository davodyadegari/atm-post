package dao;

import model.Card;

import java.sql.*;
import java.util.List;

public class CardDao implements Dao<Card> {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm", "postgres", "2523249");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card save(Card t) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into cards(card_number ,cvv2,password,expired_date) values (?,?,?,?)");
            preparedStatement.setString(1,t.getCardNumber());
            preparedStatement.setString(2,t.getCvv2());
            preparedStatement.setString(3,t.getPassword());
            preparedStatement.setDate(4, Date.valueOf(t.getExpiredDate()));
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
            PreparedStatement preparedStatement = connection.prepareStatement("update cards set account_number=? where cvv2=?");
            preparedStatement.setString(1,"0000111100002222");
            preparedStatement.setString(2,"1308");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> read() {
        return null;
    }


}