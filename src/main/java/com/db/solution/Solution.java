package com.db.solution;

import com.db.solution.interaction.LedgerProcess;
import com.db.solution.interaction.PingPong;
import com.db.solution.interaction.Queue;
import com.db.solution.model.Currency;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Solution {

    public static void main(String[] args) throws SQLException {

        //Question 1
        manipulatePingPong();

        //Question 2
        implementPriorityQueue();

        //Question 3
        ledgerBalance();
    }

    private static void manipulatePingPong() {
        Object objThread = new Object();

        Thread ping = new Thread(new PingPong(objThread, "ping"));
        Thread pong = new Thread(new PingPong(objThread, "pong"));
        ping.start();
        pong.start();
    }

    private static void implementPriorityQueue() {
        Queue<String> priorityQueue = new Queue<>();
        //add elements to the queue with priority
        priorityQueue.add((byte) 2, "Element 1");
        priorityQueue.add((byte) 3, "Element 2");
        priorityQueue.add((byte) 1, "Element 3");
        priorityQueue.add((byte) 4, "Element 4");

        //Dequeue Element 4 with priority 4
        String highestPriorityElement = priorityQueue.poll();
        System.out.println("Polled element: " + highestPriorityElement);

        //Dequeue Element 2 with priority 3
        highestPriorityElement = priorityQueue.poll();
        System.out.println("Polled element: " + highestPriorityElement);

        //Dequeue Element 1 with priority 2
        highestPriorityElement = priorityQueue.poll();
        System.out.println("Polled element: " + highestPriorityElement);

        //Dequeue Element 3 with priority 1
        highestPriorityElement = priorityQueue.poll();
        System.out.println("Polled element: " + highestPriorityElement);

        //Dequeue null, as the queue is empty
        highestPriorityElement = priorityQueue.poll();
        System.out.println("Polled element: " + highestPriorityElement);
    }

    private static void ledgerBalance() throws SQLException {
        LedgerProcess ledgerProcess = new LedgerProcess();
        //createTable
        ledgerProcess.createTable();
        //Create account from & to
        long fromAccountId = ledgerProcess.createAccount(Currency.USD);
        long toAccountId = ledgerProcess.createAccount(Currency.GBP);
        //transfer funds
        ledgerProcess.tranferFunds(fromAccountId, toAccountId, new BigDecimal("1000"), Currency.USD);
        System.out.println("1000 transferred to TO account " + toAccountId);
        //get the balance from below
        System.out.println("fromAccount Updated balance " + ledgerProcess.getBalance(fromAccountId));
        System.out.println("ToAccountId Updated balance with FX conversion" + ledgerProcess.getBalance(toAccountId));
    }
}
