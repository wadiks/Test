package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.domain.Quests;

import java.util.HashMap;
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

    public List<Quests> getQuest(String fileName) {
        return dao.loadQuest(fileName);
    }

    public void checkUser(List<Quests> quests, HashMap<String, String> message) {
        Scanner in = new Scanner(System.in);
        AtomicInteger rezTest = new AtomicInteger();
        System.out.println(message.get("enter.fio"));
        FAMILY_NAME = in.next();
        System.out.println(String.format("%s " + message.get("enter.test"), FAMILY_NAME));
        quests.forEach(i -> {
            System.out.println(String.format(message.get("cli.question") + " %s = %s", i.id, i.quest));
            System.out.println(message.get("cli.response"));
            if (Integer.valueOf(i.response) == in.nextInt()) {
                rezTest.incrementAndGet();
            }
        });
        System.out.println(String.format("%s " + message.get("response.result") + " = %s " + message.get("response.iz")
                + " %s ", FAMILY_NAME, rezTest, quests.size()));
    }
}
