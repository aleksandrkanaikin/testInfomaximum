package org.example.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ObjectModel;

import java.io.File;
import java.io.IOException;

public class JsonParseService {
    static StatisticService statisticService = new StatisticService();


    public void jsonParser(String path) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(path);

        try (JsonParser parser = mapper.getFactory().createParser(jsonFile)) {
            // Проверяем, что файл начинается с начала массива
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                // Читаем объекты один за другим до конца массива
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    ObjectModel object = mapper.readValue(parser, ObjectModel.class);
                    statisticService.countMinAndMaxWeight(object);
                    statisticService.sumWeightInEachGroup(object);
                }
            } else {
                throw new IOException("JSON file does not start with an array");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
