package com.alonelegion.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            message.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);

            // Создаём список строк клавиатуры
            List<KeyboardRow> keyboard = new ArrayList<>();

            // Первая строчка клавиатуры
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            // Добавляем кнопки в первую строчку клавиатуры
            keyboardFirstRow.add(new KeyboardButton("Привет"));

            // Вторая строчка клавиатуры
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            // Добавляем кнопки во вторую строчку клавиатуры
            keyboardSecondRow.add(new KeyboardButton("Помощь"));

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            // и устанавливаем этот список нашей клавиатуре
            replyKeyboardMarkup.setKeyboard(keyboard);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "AloneLegionBot";
    }

    @Override
    public String getBotToken() {
        return "969355508:AAHXPLlIUGmEe7Rg_W-M1Rx8O0FN5NyFg_g";
    }
}
