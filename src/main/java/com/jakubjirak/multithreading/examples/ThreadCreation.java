package com.jakubjirak.multithreading.examples;

public class ThreadCreation {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new NewThread();

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happended in thread " + t.getName() + " the error is " + e.getMessage());
            }
        });

        thread.setName("New worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("We are in thread "+Thread.currentThread().getName()+" before starting a new thread.");
        thread.start();
        System.out.println("We are in thread "+Thread.currentThread().getName()+" after starting a new thread.");

        Thread.sleep(10000);
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("We are now in thread "+ Thread.currentThread().getName());
            System.out.println("Current thread priority is "+ Thread.currentThread().getPriority());
            throw new RuntimeException("Internal Exception");
        }
    }
}
