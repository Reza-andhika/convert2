package com.BTS.converter;


import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAsync
@EnableScheduling
public class ConverterApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ConverterApplication.class, args);
        System.out.println("nice");
       
        
    
        
    }

}
