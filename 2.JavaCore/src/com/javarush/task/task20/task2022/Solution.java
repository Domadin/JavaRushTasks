package com.javarush.task.task20.task2022;

import java.io.*;


public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName,true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "C:\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\Hello.txt";
        Solution solution = new Solution(file);
        //ObjectOutputStream out = new ObjectOutputStream(solution.stream);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(solution);

        FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(inputStream);
        solution = (Solution) in.readObject();
        solution = new Solution(solution.fileName);
    }
}
