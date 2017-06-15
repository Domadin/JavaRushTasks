package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, " +
                    "день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String[] temp = message.split(": ");
                String userName = temp[0];
                String command = temp[1];
                SimpleDateFormat df = new SimpleDateFormat("d.MM.YYYY H:mm:ss");
                Calendar calendar = new GregorianCalendar();
                boolean isValid = true;
                switch (command) {
                    case "дата":
                        df = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        df = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        df = new SimpleDateFormat("MMM");
                        break;
                    case "год":
                        df = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        df = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        df = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        df = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        df = new SimpleDateFormat("ss");
                        break;
                    default:
                        isValid = false;
                }
                if (isValid)
                    sendTextMessage(String.format("Информация для %s: %s", userName, df.format(calendar.getTime())));
            }
        }
    }

    @Override
    protected String getUserName() throws IOException {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
