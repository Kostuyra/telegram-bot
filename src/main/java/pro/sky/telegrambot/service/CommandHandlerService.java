package pro.sky.telegrambot.service;

public interface CommandHandlerService {
    String handleCommand(Long chatId, String command);
}
