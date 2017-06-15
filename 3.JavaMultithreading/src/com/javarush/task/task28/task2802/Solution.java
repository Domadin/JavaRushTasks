package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {

        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());
        Thread thread3 = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
        thread3.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        public int A;
        public static AtomicInteger count = new AtomicInteger(0);
        public AtomicInteger B = new AtomicInteger(1);

        public AmigoThreadFactory() {
            count.getAndIncrement();
            A = count.get();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread result = new Thread(Thread.currentThread().getThreadGroup(), r);
            result.setDaemon(false);
            result.setPriority(Thread.NORM_PRIORITY);

            //AtomicInteger A = new AtomicInteger(1);

            String GN = result.getThreadGroup().getName();

            result.setName(GN + "-pool-" + A + "-thread-" + B.getAndIncrement());

            return result;
        }
    }
}
