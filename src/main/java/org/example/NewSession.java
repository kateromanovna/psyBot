package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class NewSession implements BotCommand{
    private HashMap<String, Integer> questions = new HashMap<>();
    @Override
    public void performCommand(SendMessage message, Bot bot) {
        questions.put("Какую эмоцию ты чувствуешь?", this.getEmotions(message, bot));
        questions.put("Оцени интенсивность по 10-бальной шкале", this.getIntencity(message, bot));
        for (String key : questions.keySet()) {
            questions.get(key);
        }
    }
    public Integer getEmotions(SendMessage message, Bot bot){
        String ans = "Какую эмоцию ты чувствуешь?";
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(ans);
        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return 1;
    }
    public Integer getIntencity(SendMessage message, Bot bot){
        String ans = "Оцени интенсивность по 10-бальной шкале";
        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText(ans);
        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
