package com.example.spring_tutorial02.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    @Value("${spring_tutorial02.name:guest}")
    private String propertyName;

    @GetMapping("/property")
    public String getProperty(){
        return propertyName;
    }
}