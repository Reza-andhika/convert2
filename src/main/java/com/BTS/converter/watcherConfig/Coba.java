/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.watcherConfig;

import com.BTS.converter.entities.DetailData;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
//@SpringBootConfiguration
//@Service
public class Coba {

    private static final String CSV_Delimiter_Custom
            = "D:\\lern\\2\\coba02.txt";

    public static void readDataFromCostumeDelimiter() {
        try {
            FileReader fileReader = new FileReader("D:\\lern\\2\\coba02.txt");

            //costume sparator
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            //object with parameter fileReader and parser
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(parser)
                    .build();
            //read all data at once
            List<String[]> allData = csvReader.readAll();

            //print data 
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                    DetailData data = new DetailData();
                    data.setField1(cell);
                    
                }
                System.out.println("");
                
            }

        } catch (Exception e) {
        }
    }
}
