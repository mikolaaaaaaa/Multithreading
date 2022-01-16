package com.mikola.multithreading.util;

public class VanIdGenerator {
    private static long id;

    public static long generateId() {
        return id++;
    }
}
