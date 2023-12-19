package org.example.commands;

import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.function.BiConsumer;

public class HelpCommand extends BotCommand {
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot){
        String ans = "Чтобы начать напиши /start,\nчтобы создать новую запись - /newnote,\nувидеть кризисный план - /crisis";
        sendAnswer(message, ans, bot);
        return null;
    }
}
