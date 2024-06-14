package com.db.solution.interaction;

import com.db.solution.dao.*;
import com.db.solution.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;

public class LedgerProcess implements LedgerService {

    private final double gbpFxRates = 0.79;

    @Override
    public void createTable() throws SQLException {
        AccountDao accountDao = new AccountDaolmpl();
        accountDao.createTable();
    }

    @Override
    public long createAccount(Currency ccy) throws SQLException {
        AccountDao accountDao = new AccountDaolmpl();
        Account account = null;
        if (ccy == Currency.USD) {
            account = new Account(111, "DB bank", "USA", new BigDecimal("5000.00"), ccy);
        } else if (ccy == Currency.GBP) {
            account = new Account(112, "JP Bank", "UK", new BigDecimal("0.00"), ccy);
        }
        return accountDao.saveAccount(account);
    }

    @Override
    public void tranferFunds(long from, long to, BigDecimal amount, Currency ccy) throws SQLException {
        AccountDaolmpl accountDao = new AccountDaolmpl();
        //From Account
        Account fromAccount = accountDao.getAccount(from);
        BigDecimal fromBalance = fromAccount.getBalance();
        fromAccount.setBalance(fromBalance.subtract(amount));
        //To Account
        Account toAccount = accountDao.getAccount(to);
        toAccount.setBalance(fxConversion(toAccount.getBalance().add(amount)));
        accountDao.updateAccount(fromAccount);
        accountDao.updateAccount(toAccount);
    }



    @Override
    public BigDecimal getBalance(long accountId) throws SQLException {
        AccountDaolmpl accountDao = new AccountDaolmpl();
        return accountDao.getAccount(accountId).getBalance();
    }

    private BigDecimal fxConversion(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(gbpFxRates));
    }
}
