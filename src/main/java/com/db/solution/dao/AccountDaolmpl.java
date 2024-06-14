package com.db.solution.dao;

import java.math.BigDecimal;
import java.sql.*;

import com.db.solution.model.*;

public class AccountDaolmpl implements AccountDao {

    String jdbcURL = "jdbc:h2:mem:test";

    Connection connection = null;


    @Override
    public void createTable() throws SQLException {

        connection = DriverManager.getConnection(jdbcURL);

        System.out.println("Connected to H2 in-memory database.");
        String createSql = "Create table accounts (accountId int, accountName varchar(50), country varchar(30), balance varchar(50), ccy varchar(50)";
        Statement statement = connection.createStatement();
        statement.execute(createSql);
        System.out.println("Created table accounts.");
    }

    @Override
    public long saveAccount(Account account) throws SQLException {

        connection = DriverManager.getConnection(jdbcURL);

        String insertSql = "Insert into accounts (accountId, accountName, country, balance, ccy) values ('" + account.getAccountId() + "','" + account.getAccountName() + "','" + account.getCountry() + "'" +
                ",'" + account.getBalance() + "','" + account.getCcy() + "')";
        Statement preparedStatement = connection.createStatement();
        int rows = preparedStatement.executeUpdate(insertSql);

        if (rows > 0) {
            System.out.println("Inserted a new account & AccountId :" + account.getAccountId() + "'& Account Balance :" + account.getBalance());
        }
        connection.commit();
        return account.getAccountId();
    }

    @Override
    public Account getAccount(long accountId) throws SQLException {

        connection = DriverManager.getConnection(jdbcURL);

        String selectSql = "select accountId, accountName, country, balance, ccy from accounts where accountId = '" + accountId + "'";
        Statement preparedStatement = connection.createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(selectSql);
        Account account = null;
        while (resultSet.next()) {
            account = new Account(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), new BigDecimal(resultSet.getString(4)), Currency.valueOf(resultSet.getString(5)));
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) throws SQLException {

        connection = DriverManager.getConnection(jdbcURL);

        String updateSql = "update accounts set balance = '" + account.getBalance() + "' where accountId = '" + account.getAccountId() + "'";
        Statement preparedStatement = connection.createStatement();
        int rows = preparedStatement.executeUpdate(updateSql);
        if (rows > 0) {
            System.out.println("Update an account with new account balance.");
        }
        connection.commit();
        connection.close();
    }
}
