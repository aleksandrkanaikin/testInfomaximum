package org.example.repository;

import org.example.model.ObjectModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectRepository {
    public static List<ObjectModel> objectsList = Collections.synchronizedList(new ArrayList<>());

    public static void addAll(List<ObjectModel> objects) {
        objectsList.addAll(objects);
    }

    public static void clear() {
        objectsList.clear();
    }
}
