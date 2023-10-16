package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText(update.getMessage().getText());

            CommandHandler command = new CommandHandler();
            command.getCommand(message, this);
        }
    }


    @Override
    public String getBotUsername() {
        // геттер имени бота
        return "psy_bot";
    }

    @Override
    public String getBotToken() {
        // геттер токена бота
        return "6445502252:AAEhiVq9eG0DAVKfMQyUqvayLt38XoFSe-I";
    }
}