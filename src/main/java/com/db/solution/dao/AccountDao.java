package com.db.solution.dao;

import com.db.solution.model.*;

import java.sql.SQLException;

public interface AccountDao {

    void createTable() throws SQLException;

    long saveAccount(Account account) throws SQLException;

    Account getAccount(long accountId) throws SQLException;

    void updateAccount(Account account) throws SQLException;
}
