package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.service.QuestsService;
import ru.otus.spring.service.QuestsServiceImpl;

@Configuration
public class QuestServiceConfig {

    @Bean
    public QuestsService  QuestsService ( QuestsDao dao) { return new QuestsServiceImpl(dao);
    }
}
