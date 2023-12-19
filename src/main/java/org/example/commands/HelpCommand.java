package org.example.commands;

import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.function.BiConsumer;

public class HelpCommand extends BotCommand {
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot){
        String ans = "Все команды бота:\n/start - начало работы,\n/newnote - создание новой записи," +
                "\n/crisis - работа с кризисным планом";
        sendAnswer(message, ans, bot);
        return null;
    }
}
