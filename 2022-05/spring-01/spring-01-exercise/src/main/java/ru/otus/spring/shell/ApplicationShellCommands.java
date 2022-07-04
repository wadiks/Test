package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.QuestsService;
import ru.otus.spring.starter.ApplicationContextHolder;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private ApplicationContext ctx;
    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "TestUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Start of testing", key = {"t", "test", "start"})
    @ShellMethodAvailability(value = "isLogin")
    public String startTest() {
        ctx = ApplicationContextHolder.getApplicationContext();
        QuestsService service = ctx.getBean(QuestsService.class);
        service.checkUser(service.getQuest(),userName);
        return "Тест пройден";
    }

    private Availability isLogin() {
        return userName == null ? Availability.unavailable("Для прохождения тетс необходимо залогинеться") : Availability.available();
    }
}
