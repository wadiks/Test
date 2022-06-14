package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestsService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestsService service = context.getBean(QuestsService.class);
        var ivan = service.getQuest();
        ivan.forEach(i->{
            System.out.println("вопрос = " + i.quest);
        });

        context.close();
    }
}
