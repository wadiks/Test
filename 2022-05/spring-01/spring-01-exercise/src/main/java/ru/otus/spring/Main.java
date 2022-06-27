package ru.otus.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.domain.Quests;
import ru.otus.spring.service.QuestsService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ComponentScan
public class Main {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return  new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestsService service = context.getBean(QuestsService.class);
        service.checkUser(service.getQuest());
        context.close();
    }


}
