package ru.otus.spring.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Quests;

import java.util.List;

@Service
public interface QuestsService {

    List<Quests> getQuest();

    public void checkUser(List<Quests> quests);
}
