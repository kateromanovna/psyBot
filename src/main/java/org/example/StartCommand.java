package org.example;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {
    @Override
    public void perform(SendMessage message, Bot bot){
        String ans = "Прит!Я бот психологической помощи! Начнём?";
        SendMessage answer = new SendMessage();
        message.setChatId(message.getChatId());
        message.setText(ans);
        try {
               bot.execute(answer);
        } catch (TelegramApiException e) {
                e.printStackTrace();
        }
    };
}

