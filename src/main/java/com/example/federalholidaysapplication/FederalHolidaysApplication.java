package com.example.federalholidaysapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FederalHolidaysApplication {

    public static void main(String[] args) {
        SpringApplication.run(FederalHolidaysApplication.class, args);
        System.out.println("Server started");
    }

}
