package ru.otus.spring.starter;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.QuestsService;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
@Profile("!test")
public class RunAfterStartup {

    private ApplicationContext ctx;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        ctx = ApplicationContextHolder.getApplicationContext();
        MessageService mService = ctx.getBean(MessageService.class);
        QuestsService service = ctx.getBean(QuestsService.class);
        service.checkUser(service.getQuest(mService.getQuestionsFileName()), mService.readMessage());
    }
}
