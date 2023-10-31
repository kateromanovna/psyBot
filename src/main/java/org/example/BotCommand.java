package org.example;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.BiConsumer;

public interface BotCommand {
    BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot);
    void sendAnswer(SendMessage answer, Bot bot);

}