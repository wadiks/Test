package module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestsDao;
import ru.otus.spring.dao.QuestsDaoSimple;
import ru.otus.spring.dao.Resourse;

@Configuration
public class QuestDaoConfig {

    @Bean
    public QuestsDao questsDao (Resourse rez){ return new QuestsDaoSimple(rez);
    }
}
