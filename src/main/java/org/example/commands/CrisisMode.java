package org.example.commands;
import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.function.BiConsumer;

public class CrisisMode extends BotCommand{
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot) {
        sendAnswer(message, "Кризис", bot);
        return null;
    }

}
