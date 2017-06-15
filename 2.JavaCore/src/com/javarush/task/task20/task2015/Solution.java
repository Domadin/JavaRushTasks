package com.javarush.task.task20.task2015;

import java.io.*;

/* 
Переопределение сериализации
*/
public class Solution implements Externalizable, Runnable {
    private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public Solution(int speed, Thread thread) {
        this.speed = speed;
        this.runner = thread;
        runner.run();
    }

    public Solution() {
    }

    @Override
    public void run() {
        System.out.println("run method");
    }

    /**
     * Переопределяем сериализацию.
     * Для этого необходимо объявить методы:
     * private void writeObject(ObjectOutputStream out) throws IOException
     * private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     * Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution save = new Solution(5);
        Solution load;
        File file = new File("C:\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2015\\file.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        out.writeObject(save);
        load = (Solution) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
        out.writeInt(speed);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal");
        speed = in.readInt();
        runner = new Thread(this);
        runner.run();
    }
}
