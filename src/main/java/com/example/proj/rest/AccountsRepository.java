package com.example.proj.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.example.proj.model.Account;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

public class AccountsRepository {
    private static Map<String, Account> map = new HashMap<String, Account>();
    Connection connection = null;

    public AccountsRepository() {
        // map.put("KKK", new Account(1,"Anna Marie", "Marayan", 19));
        // map.put("222", new Account(2,"Kim", "Perez", 24));
        // map.put("333", new Account(3,"Michelle", "Dy", 22));
    }

    public Account getAccountById(String id) {
        return map.get(id);
    }

    public String save(Account account) throws Exception {
        connection = connectToDB();
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO accounts(first_name, last_name, age, email, password) VALUES('" +
                    account.getFirstName() + "','" + account.getLastName() + "','" + account.getAge() + "','" +
                    account.getEmail() + "','" + account.getPassword() + "')";
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
            return "create";
        } else {
            return "error";
        }
    }

    public Map<String, Account> update(String id, Account account) throws Exception {
        int accountId = Integer.parseInt(id);
        connection = connectToDB();
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sql = "UPDATE accounts SET first_name= '"+account.getFirstName()+"', last_name= '"+account.getLastName()+"', age= '"+
            account.getAge()+"', email='"+account.getEmail()+"', password ='"+account.getPassword()+"' WHERE id="+accountId;
		    System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }
        return map;
    }

    public String remove(String id) throws Exception {
        int accountId = Integer.parseInt(id);
        connection = connectToDB();
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM accounts WHERE id="+accountId;
            statement.executeUpdate(sql);
            map.remove(id);
            statement.close();
            connection.close();
            return "destroy";
        } else {
            return "error";
        }
    }


    public Map<String, Account> findAllAccounts() throws Exception {
        connection = connectToDB();
        if (connection != null) {
            String sql = "SELECT * FROM accounts";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setFirstName(rs.getString(2));
                account.setLastName(rs.getString(3));
                account.setAge(rs.getInt(4));
                map.put(String.valueOf(account.getId()), account);
            }
            preparedStatement.close();
            connection.close();
        }
        return map;
    }

    public Connection connectToDB() {
        Connection connection = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/sampledb?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

        } catch (Exception e) {
        }
        return connection;
    }

}
