package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CommandHandler {

    private Map<String, BiConsumer<SendMessage, Bot>> callbacks = new HashMap();
    public CommandHandler() {

    }
    public void getCommand(SendMessage message, Bot bot){

        HashMap<String, BotCommand> commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand());
        commandMap.put("/newnote", new NewSession());
        commandMap.put("/help", new HelpCommand());

        String text = message.getText();
        System.out.println(callbacks);
        if (text.startsWith("/")) {
            for (String key : commandMap.keySet()) {
                if (key.equals(text)) {
                    var callback = commandMap.get(key).performCommand(message, bot);
                    System.out.println(callback==null);
                    if (callback!=null){
                        callbacks.put(message.getChatId(), callback);
                        System.out.println(callbacks);
                    }


                }
            }
        } else {
            System.out.println("catch a text");

            var callback = callbacks.get(message.getChatId());
            if (callback != null) {
                callback.accept(message, bot);
            }
//SingletonBot.INSTANCE.
        }
    }
}
