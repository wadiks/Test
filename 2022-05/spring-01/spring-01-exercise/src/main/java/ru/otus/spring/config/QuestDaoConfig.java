package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.dao.QuestsDaoSimple;


@Configuration
@PropertySource("classpath:application.properties")
public class QuestDaoConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public QuestsDao questsDao (){ return new QuestsDaoSimple();
    }
}
