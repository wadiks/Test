package ru.otus.spring.service;

import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.domain.Quests;

import java.util.List;

public class QuestsServiceImpl implements QuestsService {

    private final QuestsDao dao;

    public QuestsServiceImpl(QuestsDao dao) {

        this.dao = dao;
    }

    public List<Quests> getQuest() {
        return dao.loadQuest();
    }
}
