package org.example;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {
    @Override
    public void performCommand(SendMessage message, Bot bot){
        System.out.println("i am here");
        String ans = "Привет! Я бот психологической помощи! Начнём? Напиши /newnote";
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(ans);
        try {
               bot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    };
}

