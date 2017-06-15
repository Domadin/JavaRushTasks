package com.javarush.task.task20.task2021;

import java.io.*;


public class Solution implements Serializable {

    public static class SubSolution extends Solution {

        private void writeObject(ObjectOutputStream out) throws IOException {
            throw new NotSerializableException();
        }
        private void readObject(ObjectInputStream in) throws IOException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SubSolution subSolution = new SubSolution();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(subSolution);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        SubSolution loadSub = (SubSolution) in.readObject();
        System.out.println(loadSub);
        System.out.println(subSolution);
    }
}
