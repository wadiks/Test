package ru.otus.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Quests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component

public class QuestsDaoSimple implements QuestsDao {

    private final Resourse res;

    public QuestsDaoSimple(Resourse res) {

        this.res = res;
    }

    public List<Quests> loadQuest() {

        return res.loadQuestResource();
    }

}
