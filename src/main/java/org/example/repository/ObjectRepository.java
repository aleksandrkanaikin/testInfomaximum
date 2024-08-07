package org.example.repository;

import org.example.model.ObjectModel;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjectRepository {
    public static Map<String, Long> sumWeight = new HashMap<>();
    public static long minWeight;
    public static long maxWeight;

//    public static List<ObjectModel> objectsList = Collections.synchronizedList(new ArrayList<>());
//
//    public static void addAll(List<ObjectModel> objects) {
//        objectsList.addAll(objects);
//    }
//
//    public static void clear() {
//        objectsList.clear();
//    }
}
