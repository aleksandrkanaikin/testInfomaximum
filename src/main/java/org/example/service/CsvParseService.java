package org.example.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.model.ObjectModel;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class CsvParseService {
    static StatisticService statisticService = new StatisticService();

    public void csvParse(String path) {
        try(FileReader reader = new FileReader(path)){
            CsvToBean<ObjectModel> csvToBean = new CsvToBeanBuilder<ObjectModel>(reader).withType(ObjectModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<ObjectModel> iterator = csvToBean.iterator();

            while (iterator.hasNext()){
                ObjectModel object = iterator.next();
                statisticService.countMinAndMaxWeight(object);
                statisticService.sumWeightInEachGroup(object);
                statisticService.objectsDuplicate(object);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
