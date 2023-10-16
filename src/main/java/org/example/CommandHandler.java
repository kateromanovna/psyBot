package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

public class CommandHandler {
    public void getCommand(SendMessage message, Bot bot){

        HashMap<String, BotCommand> commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand());
        commandMap.put("/newnote", new NewSession());

        for (String key : commandMap.keySet()) {
            if (key.equals(message.getText())) {
                commandMap.get(key).performCommand(message, bot);
            }
            else {
            System.out.println("Invalid command");
        }
        }
    }
}
