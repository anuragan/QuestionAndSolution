package com.db.solution.interaction;

import com.db.solution.model.Currency;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface LedgerService {

    void createTable() throws SQLException;

    long createAccount(Currency ccy) throws SQLException;

    void tranferFunds(long from, long to, BigDecimal amount, Currency ccy) throws SQLException;

    BigDecimal getBalance(long accountId) throws SQLException;
}
