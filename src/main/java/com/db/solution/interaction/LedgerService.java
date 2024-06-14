package com.db.solution.interaction;

import com.db.solution.model.Currency;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface LedgerService {

    long createAccount(Currency ccy) throws SQLException;

    void tranferFunds(long from, long to, BigDecimal amount, Currency ccy) throws SQLException;

    BigDecimal getBalance(long accountId) throws SQLException;
}
