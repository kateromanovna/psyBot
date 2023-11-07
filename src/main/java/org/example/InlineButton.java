package org.example;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
public class InlineButton extends InlineKeyboardButton {
    public InlineButton(String text, String callbackData) {
    super();
    setText(text);
    setCallbackData(callbackData);
    }
}