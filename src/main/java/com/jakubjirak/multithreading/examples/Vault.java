package com.jakubjirak.multithreading.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vault {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();
        VaultData vaultData = new VaultData(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new AscendingHackerThread(vaultData));
        threads.add(new DescendingHackerThread(vaultData));
        threads.add(new PoliceThread());

        for( Thread thread: threads ) {
            thread.start();
        }
    }

    private static class VaultData {
        private int password;
        public VaultData(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                //Slow down potential hacker
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected VaultData vaultData;

        public HackerThread(VaultData vaultData) {
            this.vaultData = vaultData;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(VaultData vaultData) {
            super(vaultData);
        }

        @Override
        public void run() {
            for(int guess = 0; guess < MAX_PASSWORD; guess++) {
                if(vaultData.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(VaultData vaultData) {
            super(vaultData);
        }

        @Override
        public void run() {
            for(int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if(vaultData.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
