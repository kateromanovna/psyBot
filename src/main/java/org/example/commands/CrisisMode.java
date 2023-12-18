package org.example.commands;
import org.apache.commons.io.FileUtils;
import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;

public class CrisisMode extends BotCommand{
    private HashMap<String, Boolean> state = new HashMap<>();
    private HashMap<String, Integer> tasks = new HashMap<>();
    private String currentKey;
    List<String> lines;

    public CrisisMode() {
        tasks.put("1", 10000);
        tasks.put("2", 20000);
        tasks.put("3", 150000);
        tasks.put("4", 100000);
        state.put("1", false);
        state.put("2", false);
        state.put("3", false);
        state.put("4", false);
        File file = new File("crisisquestions.txt");
        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public BiConsumer<SendMessage, Bot> performCommand(SendMessage message, Bot bot) {
        for (String key : tasks.keySet()) {
            if (!state.get(key)) {
                currentKey = key;
                questions(message, bot);
                trackTime(tasks.get(currentKey), message, bot);
                state.put(currentKey, true);
                return null;
            }
        }
        sendAnswer(message, lines.get(4), bot);
        return null;
    }

    public void questions(SendMessage message, Bot bot) {
        String answer = switch (currentKey) {
            case "1" -> lines.get(0);
            case "2" -> lines.get(1);
            case "3" -> lines.get(2);
            case "4" -> lines.get(3);
            default -> "Произошла ошибка";
        };
        sendAnswer(message, answer, bot);
    }

    public void trackTime(Integer time, SendMessage message, Bot bot){
        Timer mTimer = new Timer();
        MyTimerTask mMyTimerTask = new MyTimerTask(message, bot, this::performCommand);
        mTimer.schedule(mMyTimerTask, time);
    }
}

