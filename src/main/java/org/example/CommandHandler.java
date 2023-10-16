package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

public class CommandHandler {
    public void getCommand(SendMessage message, Bot bot){

        HashMap<String, BotCommand> commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand());

        for (String key : commandMap.keySet()) {
            if (key.equals(message.getText())) {
                commandMap.get(key).perform(message, bot);
            }
        }
    }
}
