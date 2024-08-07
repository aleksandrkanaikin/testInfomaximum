package org.example.service;

import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class StatisticService {

    public void getStatistic() {
        System.out.println("\nObject duplicates: ");
        //getObjectDuplicates();
        System.out.println("\nTotal weight of objects in each group");
        getTotalWeightInEachGroup();
        System.out.println("\nMaximum and minimum weights of objects in the file:");
        getMinAndMaxWeightInFile();
    }

    private void getMinAndMaxWeightInFile() {

        System.out.println("Min, Weight: " + ObjectRepository.minWeight);
        System.out.println("Max, Weight: " + ObjectRepository.maxWeight);
    }

    private void getTotalWeightInEachGroup() {
        for(Map.Entry<String, Long> weightMap : ObjectRepository.sumWeight.entrySet()){
            System.out.println("Group: " + weightMap.getKey() + ", Total weight: " + weightMap.getValue());

        }
    }

//    private void getObjectDuplicates() {
//        Map<String, Long> countMap = ObjectRepository.objectsList.parallelStream()
//                .collect(Collectors.groupingBy(obj -> obj.getGroup() + "-" + obj.getType(), Collectors.counting()));
//
//        countMap.entrySet().stream()
//                .filter(entry -> entry.getValue() > 1)
//                .forEach(entry -> System.out.println("Group-Type: " + entry.getKey() + ", Count: " + entry.getValue()));
//    }

    public void countMinAndMaxWeight(ObjectModel object){
        if(ObjectRepository.maxWeight < object.getWeight()){
            ObjectRepository.maxWeight = object.getWeight();
        }

        if(ObjectRepository.minWeight > object.getWeight()){
            ObjectRepository.minWeight = object.getWeight();
        }
    }
    
    public void sumWeightInEachGroup(ObjectModel object){
        if(!ObjectRepository.sumWeight.containsKey(object.getGroup())){
            ObjectRepository.sumWeight.put(object.getGroup(), object.getWeight());
        } else if (ObjectRepository.sumWeight.containsKey(object.getGroup())) {
            ObjectRepository.sumWeight.put(object.getGroup(),
                    ObjectRepository.sumWeight.get(object.getGroup()) + object.getWeight());
        }
    }
}
