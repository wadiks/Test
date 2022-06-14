package module;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Quests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestLoad {
    private static final String STRING_ARRAY_SAMPLE = "src/main/resources/quest.csv";

    @Test
    void saveToFile() {

        try (Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        ) {
            StatefulBeanToCsv<Quests> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Quests> myUsers = new ArrayList<>();
            myUsers.add(new Quests("1", "Do you have many friends?", "yes"));
            myUsers.add(new Quests("2", "Who is your best friend? What is his/her name?", "Lena"));
            myUsers.add(new Quests("3", "When and where do you make friends?", "Summer"));
            myUsers.add(new Quests("4", "Do you like to make new friends?", "yes"));
            myUsers.add(new Quests("5", "Is it easier to find or to lose a good friend?", "find"));
            myUsers.add(new Quests("6", "Friendship helps people to be more cheerful and self-confident, doesn’t it?", "yes"));
            beanToCsv.write(myUsers);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFile() throws IOException {
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
            System.out.println(emps);
        }
    }
}

