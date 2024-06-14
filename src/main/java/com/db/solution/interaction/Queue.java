package com.db.solution.interaction;

import java.util.PriorityQueue;

public class Queue {
    public static void addAndGetPoll() {

        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.add("Gold");
        queue.add("Silver");
        queue.add("Iron");

        System.out.println("Initial PriorityQueue: " + queue);

        System.out.printf("The Top most Priority FIFO is :" + queue.poll());
    }
}
