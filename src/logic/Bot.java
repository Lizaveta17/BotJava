package logic;

import data.LoaderConfFile;
import data.BaseCommands;
import data.AnswerWithKeyboard;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Bot extends TelegramLongPollingBot {

    public Bot() {
        Logic logic = new Logic();
        LoaderConfFile.loadConfFile();
    }

    private void setReplyKeyboard(SendMessage sendMessage) {
        KeyboardRow buttonsRow = new KeyboardRow();
        buttonsRow.add(BaseCommands.HELP_RUS.getTitle());
        buttonsRow.add(BaseCommands.RECIPE.getTitle());

        ArrayList<KeyboardRow> buttons = new ArrayList<>();
        buttons.add(buttonsRow);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(buttons);
        keyboard.setResizeKeyboard(true);

        sendMessage.setReplyMarkup(keyboard);
    }

    private SendMessage setInlineKeyboard(Long chatId, AnswerWithKeyboard strWithInline, Set<String> set) {
        InlineKeyboardMarkup types = new InlineKeyboardMarkup();
        ArrayList<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (String type : set) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(new InlineKeyboardButton().setText(type).setCallbackData(type));
            rowList.add(keyboardButtonsRow);
        }
        types.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText(strWithInline.getTitle()).setReplyMarkup(types);
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage;
        Long id;
        String strIn;
        if (update.hasCallbackQuery()) {
            id = update.getCallbackQuery().getMessage().getChatId();
            strIn = update.getCallbackQuery().getData();
        } else {
            id = update.getMessage().getChatId();
            strIn = update.getMessage().getText();
        }
        System.out.println(strIn);
        AnswerWithKeyboard keyboardAnswer = Logic.getKeyboardAnswer(strIn);
        if (keyboardAnswer.equals(AnswerWithKeyboard.UNKNOWN)){
            String strOut = Logic.getAnswer(id, strIn);
            sendMessage = new SendMessage().setText(strOut).setChatId(id);
            setReplyKeyboard(sendMessage);
        }
        else {
            Set<String> set = Logic.getSetForKeyboard(id, keyboardAnswer, strIn);
            sendMessage = setInlineKeyboard(id, keyboardAnswer, set);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return LoaderConfFile.getUserName();
    }

    @Override
    public String getBotToken() {
        return LoaderConfFile.getToken();
    }
}
