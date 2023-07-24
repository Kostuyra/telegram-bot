package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.CommandHandlerService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final CommandHandlerService commandHandlerService;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, CommandHandlerService commandHandlerService) {
        this.telegramBot = telegramBot;
        this.commandHandlerService = commandHandlerService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(this::processUpdate);
        } catch (Exception e) {
            logger.error("Error during processing telegram update", e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processUpdate(Update update) {
        logger.info("Processing update: {}", update);
        Long chatId = update.message().chat().id();
        String text = update.message().text();
        String response = commandHandlerService.handleCommand(chatId, text);
        telegramBot.execute(new SendMessage(chatId, response));
    }

}
