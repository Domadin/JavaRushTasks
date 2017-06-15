package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    //Начало SocketThread
    public class SocketThread extends Thread {

        @Override
        public void run() {
            try {
                Client.this.connection = new Connection(new Socket(getServerAddress(), getServerPort()));
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Connection connection = Client.this.connection;
            while (true) {
                Message message = connection.receive();
                MessageType type = message.getType();
                if (type == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if (type == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Connection connection = Client.this.connection;
            while (true) {
                Message message = connection.receive();
                String data = message.getData();
                MessageType type = message.getType();
                if (type == MessageType.TEXT) {
                    processIncomingMessage(data);
                } else if (type == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(data);
                } else if (type == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(data);
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
    }
    //Конец SocketThread

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() throws IOException {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() throws IOException {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при отправке сообщения");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Ошибка при ожидании");
            return;
        }
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected) {
                String message = ConsoleHelper.readString();
                if (message.equals("exit")) {
                    break;
                } else if (shouldSendTextFromConsole()) {
                    sendTextMessage(message);
                }
            }
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}