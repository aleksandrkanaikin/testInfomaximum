package org.example.repository;

import jdk.jfr.Unsigned;
import org.example.model.ObjectModel;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjectRepository {
    public static Map<String, BigInteger> sumWeight = new HashMap<>();
    public static Map<String, Integer> objectsDuplicate = new HashMap<>();
    public static long minWeight = Long.MAX_VALUE;
    public static long maxWeight = Long.MIN_VALUE;

    public static void clear() {
        sumWeight.clear();
        objectsDuplicate.clear();
        minWeight = Long.MAX_VALUE;
        maxWeight = Long.MIN_VALUE;
    }
}
