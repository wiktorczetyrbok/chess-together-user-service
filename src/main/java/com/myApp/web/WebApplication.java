package com.myApp.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class WebApplication {

    @Autowired


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
