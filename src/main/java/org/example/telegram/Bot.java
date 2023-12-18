package org.example.telegram;

import org.apache.commons.io.FileUtils;
import org.example.commands.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    CommandHandler command = new CommandHandler();
    List<String> data;
    public Bot(){
        File file = new File("namepassword.txt");
        try {
            data = FileUtils.readLines(file, StandardCharsets.UTF_8);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
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
        return data.get(0);
    }

    @Override
    public String getBotToken() {
        return data.get(1);
    }
}