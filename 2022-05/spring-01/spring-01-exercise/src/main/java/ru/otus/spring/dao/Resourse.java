package ru.otus.spring.dao;

import ru.otus.spring.domain.Quests;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface Resourse {
    List<Quests> loadQuestResource();

    List<Quests> parseResource(Reader rd) throws IOException;

}
