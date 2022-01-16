package com.mikola.multithreading.main;

import com.mikola.multithreading.entity.Van;
import com.mikola.multithreading.reader.JsonReader;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
       String path = "src/main/resources/data.json";
        run(path);
    }

    private static  Deque<Van> sortVanByPerishable(List<Van> vans) {
        Deque<Van> sortedVans = new ArrayDeque<>();
        for(Van i : vans) {
            if (i.isPerishable()) {
                sortedVans.addFirst(i);
            }
            else {
                sortedVans.addLast(i);
            }
        }
        return sortedVans;
    }

    private static void run(String path) {
        JsonReader reader = new JsonReader();
        List<Van> vans = reader.read(path);
        Deque<Van> sortedVans = sortVanByPerishable(vans);
        ExecutorService executorService = Executors.newFixedThreadPool(sortedVans.size());
        sortedVans.forEach(executorService::submit);
        executorService.shutdown();
    }

}
