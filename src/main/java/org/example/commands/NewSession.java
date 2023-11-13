package org.example.commands;

import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class NewSession extends BotCommand {
    private LinkedHashMap<String, BiConsumer<SendMessage, Bot>> questions = new LinkedHashMap<>();
    private HashMap<String, Boolean> state = new HashMap<>();
    private HashMap<String, String> answers = new HashMap<>();
    private String currentKey;

    public NewSession() {
        questions.put("Эмоция", this::getEmotions);
        questions.put("Интенсивность", this::getIntencity);
        state.put("Эмоция", false);
        state.put("Интенсивность", false);


    }

    @Override
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot) {
        for (String key : questions.keySet()) {
            if (!state.get(key)) {
                currentKey = key;
                questions.get(key).accept(message, bot);
                return this::receiveEmotion;
            }
        }
        return null;
    }

    void receiveEmotion(SendMessage message, Bot bot) {
//        String ans = "Отлично";
//        sendAnswer(message, ans, bot);
        answers.put(currentKey, message.getText());
        performCommand(message, bot);
    }

    public void getEmotions(SendMessage message, Bot bot) {
        state.put(currentKey, true);
        sendInlineKeyboardEmotions(message.getChatId(), bot);
    }

    public void getIntencity(SendMessage message, Bot bot) {
        state.put(currentKey, true);
        sendInlineKeyboardIntencity(message.getChatId(), bot);
    }

    public void sendInlineKeyboardEmotions(String chatId, Bot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Какую эмоцию ты чувствуешь?:");
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineButton("страх", "страх"));
        row1.add(new InlineButton("злость", "злость"));
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineButton("грусть", "грусть"));
        row2.add(new InlineButton("любовь", "любовь"));
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineButton("радость", "радость"));
        row3.add(new InlineButton("отвращение", "отвращение"));
        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(new InlineButton("зависть", "зависть"));
        row4.add(new InlineButton("ревность", "ревность"));
        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(new InlineButton("стыд", "стыд"));
        row5.add(new InlineButton("вина", "вина"));
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        markupKeyboard.setKeyboard(keyboard);
        message.setReplyMarkup(markupKeyboard);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInlineKeyboardIntencity(String chatId, Bot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Оцени интенсивность по 10-балльной шкале:");
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineButton("1", "callback_data_1"));
        row1.add(new InlineButton("2", "callback_data_2"));
        row1.add(new InlineButton("3", "callback_data_3"));
        row1.add(new InlineButton("4", "callback_data_4"));
        row1.add(new InlineButton("5", "callback_data_5"));
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineButton("6", "callback_data_6"));
        row2.add(new InlineButton("7", "callback_data_7"));
        row2.add(new InlineButton("8", "callback_data_8"));
        row2.add(new InlineButton("9", "callback_data_9"));
        row2.add(new InlineButton("10", "callback_data_10"));
        keyboard.add(row1);
        keyboard.add(row2);
        markupKeyboard.setKeyboard(keyboard);
        message.setReplyMarkup(markupKeyboard);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
