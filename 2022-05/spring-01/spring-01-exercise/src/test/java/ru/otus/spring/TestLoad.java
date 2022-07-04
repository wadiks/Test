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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование")
@SpringBootTest
public class TestLoad {
    private static final String STRING_ARRAY_SAMPLE = "src/main/resources/quest.csv";
    public List<Quests> quests;
    private ApplicationContext ctx;
    private static final String GREETING_PATTERN = "Добро пожаловать: %s";
    private static final String DEFAULT_LOGIN = "TestUser";
    private static final String CUSTOM_LOGIN = "Вадим";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGIN_SHORT = "l";
    private static final String COMMAND_LOGIN_PATTERN = "%s %s";


    @Autowired
    private Shell shell;

    @BeforeEach
    void loadSpring() {
        ctx = ApplicationContextHolder.getApplicationContext();
        QuestsService service = ctx.getBean(QuestsService.class);
        quests = service.getQuest();
    }

    @DisplayName("Тест контекста")
    @Test
    void contextLoads() {
    }

    @DisplayName("Тест загрузки вопросов")
    @Test
    void testQuests() {
        assertNotNull(quests);
    }

    @DisplayName("Сохранение воаросов")
    @Test
    void saveToFile() {
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
            myUsers.add(new Quests("6", "How many basic sense organs does a person have?", "5"));
            beanToCsv.write(myUsers);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName(" Тест возвращает приветствие для всех форм команды логина")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommand() {
        String res = (String) shell.evaluate(() -> COMMAND_LOGIN);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, DEFAULT_LOGIN));

        res = (String) shell.evaluate(() -> COMMAND_LOGIN_SHORT);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, DEFAULT_LOGIN));

        res = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LOGIN_SHORT, CUSTOM_LOGIN));
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, CUSTOM_LOGIN));
    }

    @DisplayName("Тест загрузки файла")
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

