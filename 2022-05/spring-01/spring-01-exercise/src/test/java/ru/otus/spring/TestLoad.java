package ru.otus.spring;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.Main;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.domain.Quests;
import ru.otus.spring.service.QuestsService;
import ru.otus.spring.starter.ApplicationContextHolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestLoad {
    private static final String STRING_ARRAY_SAMPLE = "src/main/resources/quest.csv";

    public List<Quests> quests;

    private ApplicationContext ctx;

    @BeforeEach
    void loadSpring(){
        ctx = ApplicationContextHolder.getApplicationContext();
        QuestsService service =  ctx.getBean(QuestsService.class);
        quests = service.getQuest();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testQuests(){
        assertNotNull(quests);
    }


    @Test
    void saveToFile() {
        /**Возможные вопрсы
         *             1.    Сколько квадратов на шахматной доске? (64 квадрата)
         *             2.    Сколько раз в записи двузначных чисел используется цифра «3»? (19 раз)
         *             3.    Сколько ног у улитки? (одна)
         *             4.    Сколько продолжался полет Гагарина (1 ч 48 мин)
         *             5.    Сколько листьев у ландыша (2)
         *             6.    Сколько глаз у мухи (5)
         *             7.    Сколько музыкантов в квинтете? (5)
         *             8.    Сколько холодных цветов в радуге? (3, зеленый – нейтральный цвет)
         *             9.    Сколько звуков в слове «рассеянный»? (9)
         *             10.    Сколько основных органов чувств у человека? (5)
         */
        /**Possible questions
         * 1. How many squares are there on the chessboard? (64 squares)
         * 2. How many times is the digit "3" used in writing two-digit numbers? (19 times)
         * 3. How many legs does a snail have? (one)
         * 4. How long did Gagarin's flight last (1 h 48 min)
         * 5. How many leaves does a lily of the valley have (2)
         * 6. How many eyes does a fly have (5)
         * 7. How many musicians are in the quintet? (5)
         * 8. How many cold colors are there in the rainbow? (3, green is a neutral color)
         * 9. How many sounds are there in the word "scattered"? (9)
         * 10. How many basic sense organs does a person have? (5)
         */

        try (Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        ) {
            StatefulBeanToCsv<Quests> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Quests> myUsers = new ArrayList<>();
            myUsers.add(new Quests("1", "How many squares are there on the chessboard?", "64"));
            myUsers.add(new Quests("2", "How many times is a digit used in writing two-digit numbers?", "19"));
            myUsers.add(new Quests("3", "How many legs does a snail have?", "1"));
            myUsers.add(new Quests("4", "How many leaves does a lily of the valley have?", "2"));
            myUsers.add(new Quests("5", "How many eyes does a fly have?", "5"));
            myUsers.add(new Quests("6", "How many basic sense organs does a person have?","5"));
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
                emp.setResponse(record.get("RESPONSE"));
                emp.setQuest(record.get("QUEST"));
                emps.add(emp);
            }
            parser.close();
            assertNotNull(emps);
        }
    }
}

