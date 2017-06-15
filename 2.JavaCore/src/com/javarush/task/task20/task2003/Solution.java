package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String filename = console.readLine();
        FileInputStream inputStream = new FileInputStream(filename);
        load(inputStream);
    }

    public void save(OutputStream outputStream) throws Exception {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        Properties propOutput = new Properties();
        propOutput.putAll(properties);
        propOutput.store(writer,"");
    }

    public void load(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Properties propInput = new Properties();
        propInput.load(reader);
        Set<String> propSet = propInput.stringPropertyNames();
        for (String name : propSet) {
            properties.put(name, propInput.getProperty(name));
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
