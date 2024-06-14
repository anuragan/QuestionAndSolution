package com.db.solution.interaction;

import java.util.*;

public class Queue<E> {
        private static final int DEFAULT_CAPACITY = 10;
        private byte[] priorities;
        private Object[] elements;
        private int size;

        public Queue() {
            priorities = new byte[DEFAULT_CAPACITY];
            elements = new Object[DEFAULT_CAPACITY];
        }

        //add method with byte priority set
        public void add(byte priority, E element) {
            if (size == elements.length) {
                resize();
            }
            int i = size;
            while (i > 0 && priority > priorities[i - 1]) {
                priorities[i] = priorities[i - 1];
                elements[i] = elements[i - 1];
                i--;
            }
            priorities[i] = priority;
            elements[i] = element;
            size++;
        }

        private void resize() {
            int newCapacity = elements.length * 2;
            priorities = Arrays.copyOf(priorities, newCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E element = (E) elements[0];
        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
            priorities[i - 1] = priorities[i];
        }
        size--;
        return element;
    }

}
