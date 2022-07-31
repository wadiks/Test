package ru.otus.spring.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Quests;

import java.util.HashMap;
import java.util.List;

@Service
public interface QuestsService {

    List<Quests> getQuest(String fileName);

    public void checkUser(List<Quests> quests, HashMap<String, String> message);
}
