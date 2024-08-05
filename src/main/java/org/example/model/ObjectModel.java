package org.example.model;

import com.opencsv.bean.CsvBindByName;

public class ObjectModel {
    @CsvBindByName(column = "group")
    private String group;
    @CsvBindByName(column = "type")
    private String type;
    @CsvBindByName(column = "number")
    private long number;
    @CsvBindByName(column = "weight")
    private long weight;

    public ObjectModel() {
    }

    public ObjectModel(String group, String type, long number, long weight) {
        this.group = group;
        this.type = type;
        this.number = number;
        this.weight = weight;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
