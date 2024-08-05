package org.example.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.model.ObjectModel;
import org.example.repository.ObjectRepository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvParseService {
    public void csvParser(String path) {
        try (Reader reader = new FileReader(path)) {
            CsvToBean<ObjectModel> csvToBean = new CsvToBeanBuilder<ObjectModel>(reader)
                    .withType(ObjectModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<ObjectModel> objects = csvToBean.parse();
            ObjectRepository.addAll(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
