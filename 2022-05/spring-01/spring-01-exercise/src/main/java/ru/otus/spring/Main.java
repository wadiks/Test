package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.Quests;
import ru.otus.spring.service.QuestsService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ComponentScan
public class Main {

    public static String familyName = "";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestsService service = context.getBean(QuestsService.class);
        var quests = service.getQuest();
        checkUser(quests);
        context.close();
    }

    static void checkUser(List<Quests> quests){
        Scanner in = new Scanner(System.in);
        AtomicInteger rezTest = new AtomicInteger();
        System.out.println("Введите Фамилию и Имя");
        familyName = in.next();
        System.out.println(String.format("%s проидете тестирование", familyName));
        quests.forEach(i -> {
            System.out.println(String.format("вопрос номен %s = %s", i.id, i.quest));
            System.out.println("Введите Ваш ответ цифрами");
            if (Integer.valueOf(i.response) == in.nextInt()) {
                rezTest.incrementAndGet();
            }
        });
        System.out.println(String.format("%s Ваш результат = %s  из %s ", familyName, rezTest, quests.size()));
    }

}
