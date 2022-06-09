package com.example.proj.action;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.example.proj.model.Account;

public class List extends ActionSupport{

    ArrayList<Account> accounts = new ArrayList<Account>();
    public ArrayList<String> listOfFirstNames = new ArrayList<String>();
    public Account selectedAccount = new Account();
    public String nameInput;
    Connection connection = null;

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
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

    public String execute() throws Exception {

        connection = connectToDB();
        if (connection != null) {
            String sql = "SELECT * FROM accounts";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Account account = new Account();
                account.setFirstName(rs.getString(2));
                account.setLastName(rs.getString(3));
                account.setAge(rs.getInt(4));
                accounts.add(account);
                listOfFirstNames.add(account.getFirstName());
            }
            preparedStatement.close();
            connection.close();
        }
        return SUCCESS;
    }

    public String displayUser() throws Exception {
       connection = connectToDB();
        if (connection != null) {
            String sql = "SELECT * FROM accounts WHERE first_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getNameInput());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                selectedAccount.setFirstName(rs.getString(2));
                selectedAccount.setLastName(rs.getString(3));
                selectedAccount.setAge(rs.getInt(4));
            }

            preparedStatement.close();
            connection.close();
        }

        return SUCCESS;
    }
}
