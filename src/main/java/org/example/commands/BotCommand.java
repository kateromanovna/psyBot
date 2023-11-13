package org.example.commands;
import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.function.BiConsumer;

public abstract class BotCommand {
    abstract BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot);
    void sendAnswer(SendMessage message, String ans, Bot bot){
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(ans);
        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}