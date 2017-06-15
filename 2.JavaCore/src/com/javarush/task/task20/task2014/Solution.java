package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("C:\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2014\\file.txt");
        OutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = new FileInputStream(file);
        Solution savedObject = new Solution(4);
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(savedObject);
        ObjectInputStream in = new ObjectInputStream(inputStream);
        Solution loadObject = new Solution(10);
        loadObject = (Solution) in.readObject();
        System.out.println(savedObject.string.equals(loadObject.string));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
