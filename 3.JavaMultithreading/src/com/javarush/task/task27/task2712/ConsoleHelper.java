package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return consoleReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException { //составляет полностью заказ, exit для выхода
        List<Dish> result = new ArrayList<>();
        writeMessage("Список блюд:");
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите имя блюда для заказа.");
        String order = readString();
        while (!order.equalsIgnoreCase("exit")) {
            try {
                result.add(Dish.valueOf(order));
                writeMessage("Блюдо добавлено.");
            } catch (IllegalArgumentException e) {
                writeMessage("Такого блюда нет в списке.");
            }
            writeMessage("Введите имя блюда для заказа.");
            order = readString();
        }
        return result;
    }


}
