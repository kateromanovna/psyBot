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
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> startAnswer(chatId, update.getMessage().getChat().getFirstName());
                case "/help" -> helpAnswer(chatId);
                default -> { sendMessage(chatId, "прости, не знаю, что делать в такой ситуации");
                }
            }

//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void startAnswer(long chatId, String name)
    {
        String answer = "Привет, " + name + ". Я бот психологической помощи! Начнём?";
        sendMessage(chatId, answer);

    }
    public void helpAnswer(long chatId)
    {
        String answer = "Чтобы начать напиши /start";
        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend)
    {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
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