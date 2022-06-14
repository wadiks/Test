package ru.otus.spring.dao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring.domain.Quests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class QuestsDaoSimple implements QuestsDao {

    public List<Quests> loadQuest() {
        return loadQuestResource();
    }

    public List<Quests> loadQuestResource()  {
        try (final InputStream is = getClass().getResourceAsStream("/quest.csv")) {
            CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
            Reader targetReader = new InputStreamReader(is);
            CSVParser parser = new CSVParser(targetReader, format);
            List emps = new ArrayList();
            for (CSVRecord record : parser) {
                Quests emp = new Quests();
                emp.setId(record.get("ID"));
                emp.setOtvet(record.get("OTVET"));
                emp.setQuest(record.get("QUEST"));
                emps.add(emp);
            }
            parser.close();
            return emps;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
