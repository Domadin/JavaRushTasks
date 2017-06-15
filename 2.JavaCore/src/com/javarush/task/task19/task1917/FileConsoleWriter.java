package com.javarush.task.task19.task1917;

/* 
C:\test\test1.txt
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter {
    private FileWriter fileWriter;

    public FileConsoleWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        fileWriter = new FileWriter(fd);
    }

    public static void main(String[] args) throws IOException {
    }

    public void write(int c) throws IOException {
        System.out.println(c);
        fileWriter.write(c);
    }

    public void write(char[] cbuf) throws IOException {
        System.out.println(String.valueOf(cbuf));
        fileWriter.write(cbuf);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        System.out.println(String.valueOf(cbuf).substring(off, off + len));
        fileWriter.write(cbuf, off, len);
    }

    public void write(String str) throws IOException {
        System.out.println(str);
        fileWriter.write(str);
    }

    public void write(String str, int off, int len) throws IOException {
        System.out.println(str.substring(off, off + len));
        fileWriter.write(str, off, len);
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
