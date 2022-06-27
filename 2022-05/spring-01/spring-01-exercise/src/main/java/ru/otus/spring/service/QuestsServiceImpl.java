package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.domain.Quests;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestsServiceImpl implements QuestsService {

    public static String FAMILY_NAME = "";
    private final QuestsDao dao;

    public QuestsServiceImpl(QuestsDao dao) {

        this.dao = dao;
    }

    public List<Quests> getQuest() {
        return dao.loadQuest();
    }


    public void checkUser(List<Quests> quests) {
        Scanner in = new Scanner(System.in);
        AtomicInteger rezTest = new AtomicInteger();
        System.out.println("Введите Фамилию и Имя");
        FAMILY_NAME = in.next();
        System.out.println(String.format("%s проидете тестирование", FAMILY_NAME));
        quests.forEach(i -> {
            System.out.println(String.format("вопрос номен %s = %s", i.id, i.quest));
            System.out.println("Введите Ваш ответ цифрами");
            if (Integer.valueOf(i.response) == in.nextInt()) {
                rezTest.incrementAndGet();
            }
        });
        System.out.println(String.format("%s Ваш результат = %s  из %s ", FAMILY_NAME, rezTest, quests.size()));
    }
}
