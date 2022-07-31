package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

@Service
public class MessageServiceImpl implements MessageService {

    private Locale locale;
    private MessageSource messageSource;

    @Value("${file.name.en}")
    private String fileNameEN;

    @Value("${file.name.ru}")
    private String fileNameRu;


    @Autowired
    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getQuestionsFileName() {
        return locale == Locale.ENGLISH ? fileNameEN : fileNameRu;
    }


    public HashMap<String, String> readMessage() {
        Properties prop = new Properties();
        try {
            if (locale == Locale.ENGLISH)
                prop.load(MessageServiceImpl.class.getClassLoader().getResourceAsStream("messages_en.properties"));
            prop.load(new InputStreamReader(MessageServiceImpl.class.getClassLoader().getResourceAsStream("messages_ru.properties"), "UTF-8"));

            HashMap<String, String> message = new HashMap<>();
            message.put("enter.fio", prop.getProperty("enter.fio"));
            message.put("enter.test", prop.getProperty("enter.test"));
            message.put("cli.question", prop.getProperty("cli.question"));
            message.put("cli.response", prop.getProperty("cli.response"));
            message.put("response.result", prop.getProperty("response.result"));
            message.put("response.iz", prop.getProperty("response.iz"));

            return message;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
