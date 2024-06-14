package com.db.solution.interaction;

public class PingPong implements Runnable {

    private Object objThread;
    private String pingPong;

    public PingPong(Object objThread, String pingPong) {
        this.objThread = objThread;
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        synchronized (objThread) {
            int j = 0;
            while (true) {
                System.out.println(pingPong);

                objThread.notify();

                try {
                    objThread.wait(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (j == 2) {
                    break;
                }
                j++;
            }
        }
    }
}
