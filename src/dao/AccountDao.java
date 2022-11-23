package dao;
import model.Account;
import model.Card;
import model.Transaction;
import model.User;
import model.enums.AccountType;
import model.enums.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements Dao<Account> {

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm", "postgres", "2523249");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account save(Account t) {

        PreparedStatement preparedStatement = null;


            try {
            preparedStatement = connection.prepareStatement("insert into accounts(balance,user_id,account_number,password,card_id,account_type) values (?,?,?,?,?,?)");



                preparedStatement.setDouble(1, 900);
                preparedStatement.setInt(2, 2);
                preparedStatement.setString(3, "2523249566449889");
                preparedStatement.setString(4, "4444");
                preparedStatement.setInt(5, 1);
                preparedStatement.setString(6, "JARI");
                preparedStatement.execute();
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        return t;
    }



    @Override
    public void update() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update accounts set account_number=? where password=?");
        preparedStatement.setString(2,"4444");
        preparedStatement.setString(1,"1111000022220000");
        preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> read() {
        List<Account>accountList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts inner join users on user_id=users.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),resultSet.getString("family"),
                        resultSet.getString("national_code"),resultSet.getDate("birthday").toLocalDate());
                Account account = new Account(user,resultSet.getString("password"),
                        AccountType.valueOf(resultSet.getString("account_type")));
                Card card=new Card(resultSet.getString("password"));
                account.setCard(card);
                account.setId(resultSet.getInt("id"));
                List<Transaction> list1=accountTransaction();
                List<Transaction>list2=new ArrayList<>();
                for (int i = 0; i < list1.size(); i++) {
                    if(list1.get(i).getAccountId()==account.getId()){
                        list2.add(list1.get(i));
                    }
                }
                account.setTransactions(list2);
                accountList.add(account);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accountList;
    }
    public List<Transaction> accountTransaction(){
        List<Transaction> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from transactions inner join accounts on account_id=accounts.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaction transaction=new Transaction(resultSet.getDouble("amount"),
                        TransactionType.valueOf(resultSet.getString("transactions_type")));
                transaction.setDate(resultSet.getDate("date"));
                transaction.setId((long) resultSet.getInt("id"));
                transaction.setAccountId(resultSet.getInt("account_id"));
                list.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
        }





