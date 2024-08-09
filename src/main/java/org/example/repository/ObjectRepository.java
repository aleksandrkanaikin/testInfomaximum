package org.example.repository;

import java.math.BigInteger;
import java.util.*;

public class ObjectRepository {
    public static Map<String, BigInteger> sumWeight = new HashMap<>();
    public static Map<String, Integer> objectsDuplicate = new HashMap<>();
    public static long minWeight = Long.MAX_VALUE;
    public static long maxWeight = Long.MIN_VALUE;
    public static Set<String> set = new HashSet<>();


    public static void clear() {
        sumWeight.clear();
        objectsDuplicate.clear();
        minWeight = Long.MAX_VALUE;
        maxWeight = Long.MIN_VALUE;
        set.clear();
    }
}
