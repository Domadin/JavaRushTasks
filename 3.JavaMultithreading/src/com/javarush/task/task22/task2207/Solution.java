package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//C:\JavaRushTasks\3.JavaMultithreading\src\com\javarush\task\task22\task2207\file.txt

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder data = new StringBuilder();
        while (reader.ready()) {
            data.append(reader.readLine()).append(" ");
        }
        reader.close();
        List<String> splitData = new ArrayList<>(Arrays.asList(data.toString().split(" ")));
        for (int i = 0; i < splitData.size(); i++) {
            String baseWord = splitData.get(i);
            if (baseWord.length() > 0 && !baseWord.contains(" ")) {
                String reverseWord = new StringBuilder(baseWord).reverse().toString();

                for (int j = i + 1; j < splitData.size(); j++) {
                    if (reverseWord.equals(splitData.get(j))) {
                        Pair pair = new Pair(splitData.get(j), baseWord);
                        //Pair pair = new Pair(baseWord, splitData.get(j));
                        result.add(pair);
                        splitData.remove(i);
                        --j;
                        splitData.remove(j);
                        --i;
                        break;
                    }
                }
            }
        }

/*        String[] splitData = data.toString().split(" ");
        for (int i = 0; i < splitData.length; i++) {
            String baseWord = splitData[i];
            String reverseWord = new StringBuilder(baseWord).reverse().toString();
            for (int j = i; j < splitData.length; j++) {
                if (reverseWord.equals(splitData[j])) {
                    Pair pair = new Pair(baseWord, splitData[j]);
                    Pair pair1 = new Pair(splitData[j], baseWord);
                    if (!result.contains(pair) && !result.contains(pair1)) {
                        result.add(pair);
                        break;
                    }
                }
            }
        }*/


            for (Pair pair : result) {
                System.out.println(pair.toString());
            }
        }


    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}

