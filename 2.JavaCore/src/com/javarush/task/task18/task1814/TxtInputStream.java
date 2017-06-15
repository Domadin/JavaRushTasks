package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TxtInputStream extends FileInputStream {

    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        super(fileName);
        if (fileName.endsWith("txt")) {
            this.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedFileNameException {
        TxtInputStream txtInputStream = new TxtInputStream("C:\\test\\test1.txt");
        System.out.println(txtInputStream.available());
    }
}

