package com.mikola.multithreading.util;

public class TerminalIdGenerator {
    private static long id;

    public static long generateId() {
        return id++;
    }
}
