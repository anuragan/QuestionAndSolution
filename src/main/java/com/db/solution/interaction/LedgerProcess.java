package com.db.solution.interaction;

import com.db.solution.dao.*;
import com.db.solution.model.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class LedgerProcess implements LedgerService {
    @Override
    public long createAccount(Currency ccy) throws SQLException {
        AccountDao accountDao = new AccountDaolmpl();
        Account account = new Account(111, "DB bank", "USA", new BigDecimal("5000.00"), ccy);
        return accountDao.saveAccount(account);
    }

    @Override
    public void tranferFunds(long from, long to, BigDecimal amount, Currency ccy) throws SQLException {
        AccountDaolmpl accountDao = new AccountDaolmpl();
        Account account = accountDao.getAccount(from);
        BigDecimal fromBalance = account.getBalance();
        account.setBalance(fromBalance.subtract(amount));
        accountDao.updateAccount(account);
    }

    @Override
    public BigDecimal getBalance(long accountId) throws SQLException {
        AccountDaolmpl accountDao = new AccountDaolmpl();
        return accountDao.getAccount(accountId).getBalance();
    }
}
