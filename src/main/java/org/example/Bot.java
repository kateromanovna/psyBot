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
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

//            switch (message.getText()) {
//                case "/start" -> startAnswer(message);
//                case "/help" -> helpAnswer(message);
//                default -> {
//                }
//            }

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

//    public void startAnswer(SendMessage message){
//        try {
//            execute(SendMessage
//                    .builder()
//                    .chatId(message.getChatId())
//                    .parseMode("Markdown")
//                    .text("ПрЯ психологический бот").build());
//        } catch(TelegramApiException e){
//            e.printStackTrace();
//        }
//
//    }
//    public void helpAnswer(SendMessage message){
//        try {
//            execute(SendMessage
//                    .builder()
//                    .chatId(message.getChatId())
//                    .parseMode("Markdown")
//                    .text("Напиши /start для начала").build());
//        } catch(TelegramApiException e){
//            e.printStackTrace();
//        }
//    }

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