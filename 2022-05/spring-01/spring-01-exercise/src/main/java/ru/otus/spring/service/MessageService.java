package ru.otus.spring.service;

import java.util.HashMap;
import java.util.Locale;

public interface MessageService {
    String getQuestionsFileName();

    HashMap<String,String> readMessage();
}
