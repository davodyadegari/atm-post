package dao;

public class Main {
    public static void main(String[] args) {
        AccountDao dao=new AccountDao();
        System.out.println(dao.read());
    }
}