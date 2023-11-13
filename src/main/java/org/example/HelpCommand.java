package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.function.BiConsumer;

public class HelpCommand extends BotCommand{
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot){
        String ans = "Чтобы начать напиши /start, чтобы создать новую запись - /newnote";
        sendAnswer(message, ans, bot);
        return null;
    }
}
