package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

@Service
public class MessageServiceImpl implements MessageService {

    private Locale locale;

    @Value("${file.name.en}")
    private String fileNameEN;

    @Value("${file.name.ru}")
    private String fileNameRu;


    @Override
    public String getQuestionsFileName() {
        return locale == Locale.ENGLISH ? fileNameEN : fileNameRu;
    }


    public HashMap<String, String> readMessage() {
        HashMap<String, String> message = new HashMap<>();
        message.put("enter.fio", messageSource().getMessage("enter.fio", new Object[]{}, locale));
        message.put("enter.test", messageSource().getMessage("enter.test", new Object[]{}, locale));
        message.put("cli.question", messageSource().getMessage("cli.question", new Object[]{}, locale));
        message.put("cli.response", messageSource().getMessage("cli.response", new Object[]{}, locale));
        message.put("response.result", messageSource().getMessage("response.result", new Object[]{}, locale));
        message.put("response.iz", messageSource().getMessage("response.iz", new Object[]{}, locale));
        return message;
    }

    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        if (locale == Locale.ENGLISH) ms.setBasename("classpath:messages_en");
        ms.setBasename("classpath:messages_ru");
        ms.setDefaultEncoding("windows-1251");
        return ms;
    }


}
