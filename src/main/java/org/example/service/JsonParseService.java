package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JsonParseService {
    public void jsonParser(String path) {
//        CompletableFuture.runAsync(()->{ try (FileReader reader = new FileReader(path)) {
//            Gson gson = new Gson();
//            Type objectType = new TypeToken<List<ObjectModel>>(){}.getType();
//            List<ObjectModel> objects = gson.fromJson(reader, objectType);
//            ObjectRepository.addAll(objects);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }});
            try (FileReader fileReader = new FileReader(path);
                 JsonReader jsonReader = new JsonReader(fileReader)) {

                Gson gson = new Gson();
                jsonReader.beginArray(); // Начало массива объектов
                while (jsonReader.hasNext()) { // Пока есть следующий элемент
                    ObjectModel object = gson.fromJson(jsonReader, ObjectModel.class); // Десериализация объекта
                    ObjectRepository.objectsList.add(object); // Добавление объекта в список
                    System.gc();
                }
                jsonReader.endArray(); // Конец массива объектов

            } catch (IOException e) {
                e.printStackTrace();
            }

            // После чтения всех объектов добавляем их в репозиторий
            //ObjectRepository.addAll(objects);
    }
}
