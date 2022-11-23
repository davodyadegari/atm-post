package dao;

import model.Account;
import model.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TransactionsDao implements Dao<Transaction> {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm", "postgres", "2523249");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Transaction save(Transaction t) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into transactions(amount ,date,account_id,transactions_type) values (?,?,?,?)");
        preparedStatement.setDouble(1,t.getAmount());
        preparedStatement.setDate(2, (Date) t.getDate());
        preparedStatement.setInt(3,t.getAccountId());
        preparedStatement.setString(4,String.valueOf(t.getTransactionType()));
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
            PreparedStatement preparedStatement = connection.prepareStatement("update transactions set amount=? where transactions_type=?");
            preparedStatement.setDouble(1,77);
            preparedStatement.setString(2,"DEPOSIT");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> read() {
        return null;
    }

}