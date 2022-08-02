package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Quests;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public interface QuestsService {

    List<Quests> getQuest(String fileName);

    public AtomicInteger checkUser(List<Quests> quests, HashMap<String, String> message);
}
