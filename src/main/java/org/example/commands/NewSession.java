package org.example.commands;

import org.apache.commons.io.FileUtils;
import org.example.user.DataBase;
import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    DataBase db = new DataBase();
    List<String> lines;

    public NewSession() {
        initHashMaps();
        File file = new File("questionsText.txt");
        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot) {
        for (String key : questions.keySet()) {
            if (state.get("CRISIS")){
                return null;
            }
            if (!state.get(key)) {
                currentKey = key;
                questions.get(key).accept(message, bot);
                return this::receiveAnswer;
            }
        }
        sendAnswer(message, lines.get(8), bot);
        suggestion(message, bot);
        db.saveInDB(answers, message);
        initHashMaps();
        return null;
    }

    void receiveAnswer(SendMessage message, Bot bot) {
        if (currentKey.equals("intencity")) {
            System.out.println();
            if (message.getText().equals("9") | message.getText().equals("10")){
                System.out.println();
                state.put("CRISIS", true);
                new CrisisMode().performCommand(message, bot);

            }
        }
        answers.put(currentKey, message.getText());
        performCommand(message, bot);
    }

    public void initHashMaps() {
        questions.put("emotion", this::getEmotions);
        questions.put("intencity", this::getIntencity);
        questions.put("question3", this::questions);
        questions.put("question4", this::questions);
        questions.put("question5", this::questions);
        questions.put("question6", this::questions);
        questions.put("accordance", this::questionsYesNo);
        questions.put("effectivness", this::questionsYesNo);
        state.put("CRISIS", false);
        state.put("emotion", false);
        state.put("intencity", false);
        state.put("question3", false);
        state.put("question4", false);
        state.put("question5", false);
        state.put("question6", false);
        state.put("accordance", false);
        state.put("effectivness", false);
    }
    public void getEmotions(SendMessage message, Bot bot) {
        state.put(currentKey, true);
        sendInlineKeyboardEmotions(message.getChatId(), bot);
    }

    public void getIntencity(SendMessage message, Bot bot) {
        state.put(currentKey, true);
        sendInlineKeyboardIntencity(message.getChatId(), bot);
    }

    public void questions(SendMessage message, Bot bot) {
        String answer = switch (currentKey) {
            case "question3" -> lines.get(2);
            case "question4" -> lines.get(3);
            case "question5" -> lines.get(4);
            case "question6" -> lines.get(5);
            default -> "Произошла ошибка";
        };
        state.put(currentKey, true);
        sendAnswer(message, answer, bot);
    }

    public void questionsYesNo(SendMessage message, Bot bot) {
        String answer = switch (currentKey) {
            case "accordance" -> lines.get(6);
            case "effectivness" -> lines.get(7);
            default -> "Произошла ошибка";
        };
        state.put(currentKey, true);
        sendInlineKeyboardYesOrNo(message.getChatId(), bot, answer);
    }

    public void suggestion(SendMessage message, Bot bot) {
        String answer;
        if (answers.get("accordance").equals("Да") && answers.get("effectivness").equals("Да")){
            answer = lines.get(9);
        } else if (answers.get("accordance").equals("Да") && answers.get("effectivness").equals("Нет")) {
            answer = lines.get(10);
        } else if (answers.get("accordance").equals("Нет") && answers.get("effectivness").equals("Нет")) {
            answer = lines.get(11);
        } else if (answers.get("accordance").equals("Нет") && answers.get("effectivness").equals("Да")) {
            answer = lines.get(12);
        } else answer = "Произошла ошибка";

        sendAnswer(message, answer, bot);
    }

    public void sendInlineKeyboardEmotions(String chatId, Bot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(lines.get(0));
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
        message.setText(lines.get(1));
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineButton("1", "1"));
        row1.add(new InlineButton("2", "2"));
        row1.add(new InlineButton("3", "3"));
        row1.add(new InlineButton("4", "4"));
        row1.add(new InlineButton("5", "5"));
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineButton("6", "6"));
        row2.add(new InlineButton("7", "7"));
        row2.add(new InlineButton("8", "8"));
        row2.add(new InlineButton("9", "9"));
        row2.add(new InlineButton("10", "10"));
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

    public void sendInlineKeyboardYesOrNo(String chatId, Bot bot, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineButton("Да", "Да"));
        row1.add(new InlineButton("Нет", "Нет"));
        keyboard.add(row1);
        markupKeyboard.setKeyboard(keyboard);
        message.setReplyMarkup(markupKeyboard);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
