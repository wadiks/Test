package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.dao.QuestsDaoSimple;
import ru.otus.spring.dao.Resourse;


@Configuration
public class QuestDaoConfig {

    public QuestsDao questsDao(Resourse rez) {  return new QuestsDaoSimple(rez);
    }
}
