package com.jakubjirak.multithreading.examples;

public class ThreadCreation {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in new thread
                System.out.println("We are now in thread "+ Thread.currentThread().getName());
            }
        });
        thread.setName("New worker thread");
        System.out.println("We are in thread "+Thread.currentThread().getName()+" before starting a new thread.");
        thread.start();
        System.out.println("We are in thread "+Thread.currentThread().getName()+" after starting a new thread.");

        Thread.sleep(10000);
    }
}
