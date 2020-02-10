package com.jakubjirak.multithreading.examples;

import java.util.List;

public class MultiExecutor {
    private static List<Runnable> tasks = null;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for (Runnable r: tasks) {
            new Thread(r).start();
        }
    }
}
