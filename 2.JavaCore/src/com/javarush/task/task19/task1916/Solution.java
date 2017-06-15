package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file1 = console.readLine();
        String file2 = console.readLine();
        console.close();
        BufferedReader file1Read = new BufferedReader(new FileReader(file1));
        BufferedReader file2Read = new BufferedReader(new FileReader(file2));

        String reader1, reader2$1, reader2$2 = "";
        boolean removed = false;

        //Начало алгоритма
        while (true) {
            if (removed) {                          //Костыль, чтобы не перескакивало одно значение во 2 файле после REMOVE
                reader1 = file1Read.readLine();
                reader2$1 = reader2$2;
                removed = false;
            } else {
                reader1 = file1Read.readLine();
                reader2$1 = file2Read.readLine();
            }
            if (reader1 != null && reader2$1 != null) {      //Проверка на то, закончился ли один из списков
                if (reader1.equals(reader2$1)) {
                    addList(Type.SAME, reader1);
                } else {
                    reader2$2 = file2Read.readLine();
                    if (reader1.equals(reader2$2)) {
                        addList(Type.ADDED, reader2$1);
                        addList(Type.SAME, reader1);          //После ADDED или REMOVE всегда идет SAME
                    } else {
                        addList(Type.REMOVED, reader1);
                        addList(Type.SAME, reader2$1);
                        file1Read.readLine();                 //Скипаем строчку, т.к. SAME
                        removed = true;                       //Активируем костыль вначале
                    }
                }
            } else if (reader1 == null && reader2$1 == null) {
                break;
            } else if (reader1 == null) {
                lines.add(new LineItem(Type.ADDED, reader2$1));
                break;
            } else {
                lines.add(new LineItem(Type.REMOVED, reader1));
                break;
            }
        }
        //Конец алгоритма

        file1Read.close();
        file2Read.close();

        //Проверка lines
        for (LineItem line : lines) {
            System.out.println(line.line + "___" + line.type);
        }
    }

    public static void addList(Type type, String line) {
        lines.add(new LineItem(type, line));
    }


    public enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
