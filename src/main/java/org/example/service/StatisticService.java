package org.example.service;

import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.math.BigInteger;
import java.util.*;

public class StatisticService {
    private Set<String> set = new HashSet<>();

    public void getStatistic() {
        System.out.println("\nObject duplicates: ");
        getObjectDuplicates();
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
        for(Map.Entry<String, BigInteger> weightMap : ObjectRepository.sumWeight.entrySet()){
            System.out.println("Group: " + weightMap.getKey() + ", Total weight: " + weightMap.getValue());

        }
    }

    private void getObjectDuplicates() {
        ObjectRepository.objectsDuplicate.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println("Group-Type: " + entry.getKey() + ", Count: " + entry.getValue()));
    }

    public void countMinAndMaxWeight(ObjectModel object){
        long weight = object.getWeight();
        if(weight > ObjectRepository.maxWeight){
            ObjectRepository.maxWeight = weight;
        } else if (weight < ObjectRepository.minWeight){
            ObjectRepository.minWeight = weight;
        }
    }
    
    public void sumWeightInEachGroup(ObjectModel object){
        if(!ObjectRepository.sumWeight.containsKey(object.getGroup())){
            ObjectRepository.sumWeight.put(object.getGroup(), BigInteger.valueOf(object.getWeight()));
        } else if (ObjectRepository.sumWeight.containsKey(object.getGroup())) {
            ObjectRepository.sumWeight.put(object.getGroup(),
                    ObjectRepository.sumWeight.get(object.getGroup()).add(BigInteger.valueOf(object.getWeight())));
        }
    }

    public void objectsDuplicate(ObjectModel object){

        String key = object.getGroup() + "-" + object.getType();

        if(set.size() < 10000){
           if(!set.add(key)){
               if(ObjectRepository.objectsDuplicate.containsKey(key)){
                   ObjectRepository.objectsDuplicate.put(key, ObjectRepository.objectsDuplicate.get(key)+1);
               }
               else {
               ObjectRepository.objectsDuplicate.put(key, 2);
               }
           }
        }
        else {
            if(ObjectRepository.objectsDuplicate.containsKey(key)){
                ObjectRepository.objectsDuplicate.put(key, ObjectRepository.objectsDuplicate.get(key)+1);
            }
        }
    }
}
