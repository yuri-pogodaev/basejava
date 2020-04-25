package com.basejava.webapp;

public class MainDeadLock {
    public static void main(String[] args) throws InterruptedException {
        new DeadLock().startDeadLock();
    }

    private static class DeadLock {
        private final static Object obj1 = new Object();
        private final static Object obj2 = new Object();

        Thread tread1 = new Thread() {
            @Override
            public void run() {
                synchronized (obj1) {
                    System.out.println(getName() + " synchronized  obj1");
                    for (int i = 0; i < 10000; i++) {
                        double a = 10000;
                    }
                    synchronized (obj2) {
                        System.out.println(getName() + " synchronized  obj2");
                    }
                }
            }
        };

        Thread tread2 = new Thread() {
            @Override
            public void run() {
                synchronized (obj2) {
                    System.out.println(getName() + " synchronized  obj2");
                    for (long i = 0; i < 10000; i++) {
                        double a = 10000;
                    }
                    synchronized (obj1) {
                        System.out.println(getName() + " synchronized  obj1");
                    }
                }
            }
        };

        void startDeadLock() throws InterruptedException {
            tread2.start();
            tread1.start();
            Thread.sleep(500);
            System.out.println(tread1.getState());
            System.out.println(tread2.getState());
        }
    }
}
