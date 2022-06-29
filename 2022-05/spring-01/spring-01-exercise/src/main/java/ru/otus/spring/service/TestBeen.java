package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class TestBeen {
    @Bean
    public void testBeen(@Value("${file.url}") String res){
        System.out.println("res = " + res);
    }
}
