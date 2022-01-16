package com.mikola.multithreading.entity;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsBase {
    private static LogisticsBase instance;
    private static final Lock locker = new ReentrantLock();
    private static final int DEFAULT_TERMINALS_COUNT = 2;
    private int availableTerminals;
    Semaphore semaphore;
    List<Terminal> terminals = new ArrayList<>();

    private LogisticsBase() {
        semaphore = new Semaphore(DEFAULT_TERMINALS_COUNT);
        for (int i = 0; i < DEFAULT_TERMINALS_COUNT; i++) {
            terminals.add(new Terminal());
        }
    }

    public static LogisticsBase getInstance() {
        locker.lock();
        try {
            if (instance == null) {
                instance = new LogisticsBase();
            }
        } finally {
            locker.unlock();
        }
        return instance;
    }

    public void acquireTerminal(Van van) {
        try {
            van.setState(Van.State.WAITING);
            semaphore.acquire();
            locker.lock();
            Terminal terminal;
            try {
                terminal = terminals.get(availableTerminals);
                availableTerminals++;
            } finally {
                locker.unlock();
            }
            terminal.process(van);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            availableTerminals--;
            semaphore.release();
        }
    }


}
