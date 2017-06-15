package com.javarush.task.task25.task2505;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
Без дураков
*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
        Thread.sleep(1000);
    }

    public class MyThread extends Thread {
        private String secretKey;
        private Logger log = Logger.getLogger(MyThread.class.getName());

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);
        }

        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                    System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
                    log.log(Level.SEVERE, "an exception was thrown", e);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }
    }

}

