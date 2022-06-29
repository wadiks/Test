package ru.otus.spring.starter;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.QuestsService;

@Component
@Profile("!test")
public class RunAfterStartup {

        private  ApplicationContext ctx;
    @EventListener(ApplicationReadyEvent.class)
        public void runAfterStartup() {
         ctx = ApplicationContextHolder.getApplicationContext();
            QuestsService service = ctx.getBean(QuestsService.class);
            service.checkUser(service.getQuest());
        }
}
