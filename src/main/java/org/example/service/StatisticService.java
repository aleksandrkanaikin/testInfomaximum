package org.example.service;

import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class StatisticService {

    public void getStatistic() {
        System.out.println("\nObject duplicates: ");
        getObjectDuplicates();
        System.out.println("\nTotal weight of objects in each group");
        getTotalWeightInEachGroup();
        System.out.println("\nMaximum and minimum weights of objects in the file:");
        getMinAndMaxWeightInFile();
    }

    private void getMinAndMaxWeightInFile() {
        AtomicLong minWeight = new AtomicLong(Long.MAX_VALUE);
        AtomicLong maxWeight = new AtomicLong(Long.MIN_VALUE);

        ObjectRepository.objectsList.parallelStream().forEach(o -> {
            long weight = o.getWeight();
            minWeight.updateAndGet(value -> Math.min(value, weight));
            maxWeight.updateAndGet(value -> Math.max(value, weight));
        });

        System.out.println("Min, Weight: " + minWeight.get());
        System.out.println("Max, Weight: " + maxWeight.get());
    }

    private void getTotalWeightInEachGroup() {
        Map<String, Long> weightMap = ObjectRepository.objectsList.parallelStream()
                .collect(Collectors.groupingBy(ObjectModel::getGroup, Collectors.summingLong(ObjectModel::getWeight)));

        weightMap.forEach((key, value) -> System.out.println("Group: " + key + ", Total weight: " + value));
    }

    private void getObjectDuplicates() {
        Map<String, Long> countMap = ObjectRepository.objectsList.parallelStream()
                .collect(Collectors.groupingBy(obj -> obj.getGroup() + "-" + obj.getType(), Collectors.counting()));

        countMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println("Group-Type: " + entry.getKey() + ", Count: " + entry.getValue()));
    }
}
