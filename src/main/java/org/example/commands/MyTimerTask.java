package org.example.commands;

import org.example.telegram.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.TimerTask;
import java.util.function.BiConsumer;

class MyTimerTask extends TimerTask {
    SendMessage message;
    Bot bot;
    BiConsumer<SendMessage, Bot> h;
    public MyTimerTask(SendMessage message, Bot bot, BiConsumer<SendMessage, Bot> h){
        this.message = message;
        this.bot = bot;
        this.h = h;
    }
    @Override
    public void run() {
        System.out.println("время пришло");
        h.accept(message, bot);
    }
}

