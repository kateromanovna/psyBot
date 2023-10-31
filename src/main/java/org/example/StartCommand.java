package org.example;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.function.BiConsumer;

public class StartCommand implements BotCommand {
    @Override
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot){
        System.out.println("i am here");
        String ans = "Привет! Я бот психологической помощи! Начнём? Напиши /newnote";
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(ans);
        sendAnswer(answer, bot);
        return null;
    };
    public void sendAnswer(SendMessage answer, Bot bot){
        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

