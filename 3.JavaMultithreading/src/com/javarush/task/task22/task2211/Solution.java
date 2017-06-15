package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        //BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "CP1251"));
        //BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(args[0]), "UTF-8");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(args[1]), "Windows-1251");
        while (fileReader.ready()) {
            fileWriter.write(fileReader.read());
        }
        fileReader.close();
        fileWriter.close();
    }
}
