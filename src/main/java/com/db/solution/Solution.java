package com.db.solution;

import com.db.solution.interaction.LedgerProcess;
import com.db.solution.interaction.PingPong;
import com.db.solution.interaction.Queue;
import com.db.solution.model.Currency;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Solution {

    public static void main(String[] args) throws SQLException{
        //Question 3
        ledgerBalance();

        //Question 2
        implementPriorityQueue();

        //Question 1
        manipulatePingPong();
    }

    private static void manipulatePingPong() throws SQLException {
        LedgerProcess ledgerProcess = new LedgerProcess();
        //Create account
        long fromAccountId = ledgerProcess.createAccount(Currency.USD);
        long  toAccountId = 112;
        //transfer funds
        ledgerProcess.tranferFunds(fromAccountId,toAccountId,new BigDecimal("1000"),Currency.USD);
        System.out.println("1000 transferred to TO account "+toAccountId);
        //get the balance from below method
        System.out.println("Update balance " +ledgerProcess.getBalance(fromAccountId));
    }

    private static void implementPriorityQueue() {
        Queue.addAndGetPoll();
    }

    private static void ledgerBalance() {
        Object objThread = new Object();

        Thread ping = new Thread(new PingPong(objThread, "ping"));
        Thread pong = new Thread(new PingPong(objThread, "pong"));
        ping.start();
        pong.start();
    }
}
