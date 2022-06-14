package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.dao.QuestsDaoSimple;


@Configuration
public class QuestDaoConfig {

    @Bean
    public QuestsDao questsDao (){ return new QuestsDaoSimple();
    }
}
