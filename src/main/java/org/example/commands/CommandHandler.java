package org.example.commands;

import org.example.User.DataBase;
import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class CommandHandler {

    private HashMap<String, BiConsumer<SendMessage, Bot>> callbacks = new HashMap();
    HashMap<String, BotCommand> commandMap = new HashMap<>();
    public CommandHandler() {
        commandMap.put("/start", new StartCommand());
        commandMap.put("/newnote", new NewSession());
        commandMap.put("/help", new HelpCommand());
    }
    public void handleCommand(SendMessage message, Bot bot){

        String text = message.getText();
        if (text.startsWith("/")) {
            if (commandMap.containsKey(text)) {
                var callback = commandMap.get(text).performCommand(message, bot);
                if (callback!=null){
                    callbacks.put(message.getChatId(), callback);
                }
            }
        } else {
            var callback = callbacks.get(message.getChatId());
            if (callback != null) {
                callback.accept(message, bot);
            }
        }
    }
}
