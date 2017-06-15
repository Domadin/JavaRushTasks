package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    String userName = answer.getData();
                    if (!userName.isEmpty() && !connectionMap.containsKey(userName)) {
                        connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return userName;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                String userFromMap = pair.getKey();
                if (!userFromMap.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, userFromMap));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message incMessage = connection.receive();
                if (incMessage.getType() == MessageType.TEXT) {
                    Message messageToDeliver = new Message(MessageType.TEXT, String.format("%s: %s", userName, incMessage.getData()));
                    sendBroadcastMessage(messageToDeliver);
                } else {
                    ConsoleHelper.writeMessage("Сообщение не является текстом");
                }
            }
        }

        @Override
        public void run() {
            String userName = "";
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом " + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            ConsoleHelper.writeMessage("Соединенине с удаленным адресом закрыто");
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            Connection currentConnection = pair.getValue();
            String userName = pair.getKey();
            try {
                currentConnection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage
                        ("Не смогли отправить сообщение пользователю " + userName);
            }
        }
    }

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
