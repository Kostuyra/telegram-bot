package pro.sky.telegrambot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class TelegramBotConfiguration {

    @Value("${telegram.bot.token}")
    private String token;
    @Value("${telegram.bot.msg.start}")
    private String startMessage;
    @Value("${telegram.bot.msg.error}")
    private String errorMessage;
    @Value("${telegram.bot.msg.success}")
    private String successMessage;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

}
