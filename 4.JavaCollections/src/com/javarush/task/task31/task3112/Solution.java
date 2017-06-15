package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://pp.userapi.com/c837632/v837632462/40bff/jjUc483VCYU.jpg", Paths.get("C:\\test"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("javarushFile-", ".tmp");
        InputStream downloadStream = url.openStream();
        Files.copy(downloadStream, tempFile);
        Path resultFile = Paths.get(downloadDirectory.toString() + urlString.substring(urlString.lastIndexOf("/")));
        Files.move(tempFile, resultFile);
        return resultFile;
    }
}
