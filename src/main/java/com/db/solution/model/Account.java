package com.db.solution.model;

import java.math.BigDecimal;

public class Account {
    private long accountId;
    private String accountName;
    private String country;
    private BigDecimal balance;
    private Currency ccy;

    public Account(long accountId, String accountName, String country, BigDecimal balance, Currency ccy) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.country = country;
        this.balance = balance;
        this.ccy = ccy;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCcy() {
        return ccy;
    }

    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }
}
