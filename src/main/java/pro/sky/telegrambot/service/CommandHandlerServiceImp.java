package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandHandlerServiceImp implements CommandHandlerService {
    private final NotificationTaskService notificationTaskService;
    private final TelegramBotConfiguration telegramBotConfiguration;
    private static final String START_COMMAND = "/start";
    private final Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public CommandHandlerServiceImp(NotificationTaskService notificationTaskService, TelegramBotConfiguration telegramBotConfiguration) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBotConfiguration = telegramBotConfiguration;
    }

    @Override
    public String handleCommand(Long chatId, String command) {
        switch (command) {
            case START_COMMAND:
                return telegramBotConfiguration.getStartMessage();
            default:
                return handlerCreateTaskCommand(chatId, command);
        }

    }

    private String handlerCreateTaskCommand(Long chatId, String command) {
        if (command != null){
            Matcher matcher = pattern.matcher(command);
            if (matcher.matches()) {
                LocalDateTime date = LocalDateTime.parse(matcher.group(1), dateTimeFormatter);
                String text = matcher.group(3);
                notificationTaskService.save(new NotificationTask(chatId, text, date));
                return telegramBotConfiguration.getSuccessMessage();
            }
        }
        return telegramBotConfiguration.getErrorMessage();
    }
}
