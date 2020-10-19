package Logic;

import Data.LoaderCongFile;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private Logic logic;

    public Bot() {
        logic = new Logic();
        LoaderCongFile.loadConfFile();
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        Long id = update.getMessage().getChatId();
        String strIn = update.getMessage().getText();
        System.out.println(strIn);
        String strOut = logic.getAnswer(id, strIn);
        SendMessage sendMessage = new SendMessage().setChatId(id).setText(strOut);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return LoaderCongFile.getUserName();
    }

    @Override
    public String getBotToken() {
        return LoaderCongFile.getToken();
    }
}