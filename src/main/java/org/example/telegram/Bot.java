package org.example.telegram;

import org.example.User.DataBase;
import org.example.commands.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    CommandHandler command = new CommandHandler();
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            command.handleCommand(message, this);
        }
        if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery();
            SendMessage message = new SendMessage();
            message.setChatId(callbackQuery.getMessage().getChatId());
            message.setText(callbackQuery.getData());
            command.handleCommand(message, this);
        }
    }


    @Override
    public String getBotUsername() {
        // геттер имени бота
        return "testBot";
    }

    @Override
    public String getBotToken() {
        // геттер токена бота
        return "6643865749:AAGm8ngdjIgPqikcCLXburHc3JGBdHmcge0";
    }
}