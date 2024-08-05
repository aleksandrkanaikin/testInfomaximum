package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParseService {
    public void jsonParser(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type objectType = new TypeToken<List<ObjectModel>>(){}.getType();
            List<ObjectModel> objects = gson.fromJson(reader, objectType);
            ObjectRepository.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
