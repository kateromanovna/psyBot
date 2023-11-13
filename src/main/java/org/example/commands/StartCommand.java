package org.example.commands;


import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.function.BiConsumer;

public class StartCommand extends BotCommand {
    @Override
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot){
        String ans = "Привет! Я бот психологической помощи! Начнём? Напиши /newnote";
        sendAnswer(message, ans, bot);
        return null;
    }
}

