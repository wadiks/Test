package ru.otus.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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

    public List<Quests> loadQuest(String fileName) {

        return loadQuestResource(fileName);
    }

    public List<Quests> loadQuestResource(String fileName) {
        try (final InputStream is = getClass().getResourceAsStream(fileName)) {
            Reader targetReader = new InputStreamReader(is);
            return parseResource(targetReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Quests> parseResource(Reader rd) throws IOException {
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        CSVParser parser = new CSVParser(rd, format);
        List emps = new ArrayList();
        for (CSVRecord record : parser) {
            Quests emp = new Quests();
            emp.setId(record.get("ID"));
            emp.setResponse(record.get("RESPONSE"));
            emp.setQuest(record.get("QUEST"));
            emps.add(emp);
        }
        parser.close();
        return emps;
    }

}
