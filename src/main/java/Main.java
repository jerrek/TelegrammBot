
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Random;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //устанавливает имя бота
    public String getBotUsername() {
        return "Rab1";
    }

    //обработчик поступающих в бота команд
    public void onUpdateReceived(Update e) {
        try {
            Message msg = e.getMessage();
            String txt = msg.getText();
            if (txt.equals("/help")) {
                System.out.println("/help");
                sendMsg(msg, "/start - начинает работу бота");
                sendMsg(msg, "/aliance - выводит случайную фразу альянса");
                sendMsg(msg, "/horde - выводит случайную фразу орды");
                sendMsg(msg, "/scourge - выводит случайную фразу нежити");
                sendMsg(msg, "/nightelf - выводит случайную фразу ночных эльфов");
                sendMsg(msg, "/neutral - выводит случайную фразу нейтральных моностров");
                sendMsg(msg, "/randome - выводит случайную фразу из всего WarCraft");
            }
            if (txt.equals("/start")) {
                System.out.println("/start");
                sendMsg(msg, "WarCraft");
            }
            if (txt.equals("/aliance")) {
                System.out.println("/aliance");
                sendMsg(msg, genWC3Phrases.getRandomePhrase("Альянс"));
            }
            if (txt.equals("/horde")) {
                System.out.println("/horde");
                sendMsg(msg, genWC3Phrases.getRandomePhrase("Орда"));
            }
            if (txt.equals("/scourge")) {
                System.out.println("/scourge");
                sendMsg(msg, genWC3Phrases.getRandomePhrase("Нежить"));
            }
            if (txt.equals("/nightelf")) {
                System.out.println("/nightelf");
                sendMsg(msg, genWC3Phrases.getRandomePhrase("Ночные эльфы"));
            }
            if (txt.equals("/neutral")) {
                System.out.println("/neutral");
                sendMsg(msg, genWC3Phrases.getRandomePhrase("Нейтральные монстры"));
            }
            if (txt.equals("/randome")) {
                System.out.println("/randome");
                int k = new Random().nextInt(5);
                System.out.println(k);
                switch (k) {
                    case 0: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Альянс"));
                        break;
                    }
                    case 1: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Орда"));
                        break;
                    }
                    case 2: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Нежить"));
                        break;
                    }
                    case 3: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Ночные эльфы"));
                        break;
                    }
                    case 4: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Нейтральные монстры"));
                        break;
                    }
                    default: {
                        sendMsg(msg, genWC3Phrases.getRandomePhrase("Альянс"));
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Помоему что то пошло не так... " + "\n" + ex);
        }
    }

    //устанавливает токен для соединения с ботом
    @Override
    public String getBotToken() {
        return "";
    }

    // отправляет сообщение
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение,
        // грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}