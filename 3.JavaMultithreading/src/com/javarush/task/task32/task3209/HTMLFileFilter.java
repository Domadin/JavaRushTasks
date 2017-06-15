package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        if (file.isDirectory()) {
            return true;
        }
        if (name.contains(".")) {
            String ext = name.substring(name.indexOf("."));
            return ext.equalsIgnoreCase(".html") || ext.equalsIgnoreCase(".htm");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
