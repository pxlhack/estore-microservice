package ru.isands.test.estore.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;

public class CsvFileHelper {

    private static final String DEFAULT_CHARSET = "Windows-1251";

    public static boolean processCsvFile(byte[] content, Consumer<List<String[]>> rowsProcessor) {
        try (CSVReader reader = createCsvReader(content)) {
            List<String[]> allRows = reader.readAll();
            rowsProcessor.accept(allRows);
            return true;
        } catch (IOException | CsvException e) {
            System.err.println("Ошибка при обработке CSV файла: " + e.getMessage());
            return false;
        }
    }


    private static CSVReader createCsvReader(byte[] content) {
        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(content),
                Charset.forName(DEFAULT_CHARSET));

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        return new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build();
    }

    public static boolean isCsvFile(String fileName) {
        return fileName.toLowerCase().endsWith(".csv");
    }
}