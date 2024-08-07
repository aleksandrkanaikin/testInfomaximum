package org.example;

import org.example.repository.ObjectRepository;
import org.example.service.CsvParseService;
import org.example.service.JsonParseService;
import org.example.service.StatisticService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static StatisticService statisticService = new StatisticService();
    static CsvParseService csvParse = new CsvParseService();
    static JsonParseService jsonParse = new JsonParseService();

    private static String fileFormat = null;

    public static void main(String[] args) {
        Scanner inData = new Scanner(System.in);
        String[] validExtensions = {"csv", "json"};

        while (true) {
            System.out.print("\nEnter the file path or 'exit' to exit: ");
            String input = inData.nextLine();
            ObjectRepository.clear();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (isValidFileFormat(input, validExtensions)) {
                if (fileFormat.equals("json")) {
                    jsonParse.jsonParser(input);
                } else {
                    csvParse.csvParse(input);
                }
                statisticService.getStatistic();
            } else {
                System.out.println("Incorrect file format!");
            }
        }
        inData.close();
    }

    private static boolean isValidFileFormat(String filePath, String[] expectedExtensions) {
        Path path = Paths.get(filePath);

        StringBuilder regexBuilder = new StringBuilder(".*\\.(");
        for (int i = 0; i < expectedExtensions.length; i++) {
            regexBuilder.append(expectedExtensions[i]);
            if (i < expectedExtensions.length - 1) {
                regexBuilder.append("|");
            }
        }
        regexBuilder.append(")$");

        String regex = regexBuilder.toString();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(path.toString());

        if (matcher.matches()) {
            fileFormat = matcher.group(1).toLowerCase();
        } else {
            fileFormat = null;
        }

        return matcher.matches();
    }
}