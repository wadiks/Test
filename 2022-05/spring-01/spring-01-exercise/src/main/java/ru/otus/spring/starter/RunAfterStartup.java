package ru.otus.spring.starter;

import org.springframework.beans.factory.annotation.Autowired;
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

    MessageService messageService;
    QuestsService questsService;

    @Autowired
    public RunAfterStartup(MessageService messageService, QuestsService questsService) {
        this.messageService = messageService;
        this.questsService = questsService;
    }

   @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
     questsService.checkUser(questsService.getQuest(messageService.getQuestionsFileName()), messageService.readMessage());
    }
}
