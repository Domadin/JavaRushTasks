package com.javarush.task.task27.task2703;

/* 
Создаем deadlock
*/
public class Solution {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void bow(Friend bower) {  // Method no longer synchronized.
            int firstHash = System.identityHashCode(this);
            int secondHash = System.identityHashCode(bower);

            Object firstMonitor = firstHash < secondHash ? this : bower;
            Object secondMonitor = firstHash < secondHash ? bower : this;
            synchronized (firstMonitor) {
                synchronized (secondMonitor) {
                    System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
                    bower.bowBack(this);
                }
            }
        }


        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();

        new Thread(() -> gaston.bow(alphonse)).start();
    }
}
