package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static String[] words;
    int maxCommon = 0;
    static StringBuilder builder = new StringBuilder();

    //Graph
    Map<Integer, Set<Integer>> graph;
    int graphSize;
    int startingVertex;

    //Search
    boolean[] discovered;
    int[] parent;

    //Topological sort
    ArrayDeque<Integer> sort;

    public static void main(String args[]) throws IOException {
        //C:\Users\Domadin\IdeaProjects\TestOnly\src\file.txt
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        BufferedReader fileRead = new BufferedReader(new FileReader(fileName));
        StringBuilder data = new StringBuilder();
        while (fileRead.ready()) {
            data.append(fileRead.readLine()).append(" ");
        }
        words = data.toString().trim().split(" ");
        Arrays.sort(words);
        Solution lp = new Solution();
        lp.run();
        String[] forBuilder = builder.toString().trim().split(" ");
        StringBuilder result = getLine(forBuilder);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word).append(" ");
        }
        return result;
    }

    public void run() {
        graphSize = words.length;
        graph = new HashMap<Integer, Set<Integer>>();
        for (int from = 0; from < words.length; from++) {
            char first = Character.toLowerCase(words[from].charAt(words[from].length() - 1));
            for (int to = 0; to < words.length; to++) {
                if (from != to) {
                    char second = Character.toLowerCase(words[to].charAt(0));
                    if (first == second) {
                        insertEdge(from, to);
                    }
                }
            }
        }
        for (int i = 0; i < graphSize; i++) {
            startingVertex = i;
            longestDistance();
        }

    }

    public void longestDistance() {
        //initiate DFS and topological sort
        discovered = new boolean[graphSize + 1];
        parent = new int[graphSize + 1];
        sort = new ArrayDeque<Integer>();

        //do topological sort
        depthFirstSearch(startingVertex);

        //Compute the length of the longest path ending at v by looking at its incoming
        //neighbors and adding one to the maximum length recorded for those neighbors.
        //Суть данного цикла в том, чтобы для каждого значения заполнить путь до стартовой точки максимальный, получилось {0,3,2,1,4}
        int[] longestDistance = new int[graphSize + 1];
        for (int from : sort)
            for (int to : graph.get(from))
                longestDistance[to] = Math.max(longestDistance[to], longestDistance[from] + 1);

        //Starting from longest distance, step back to node with next highest distance
        //D цикле вытягивается максимальное значение шагов и конечная точка
        int max = 0;
        int endPoint = 0;
        for (int i = 0; i < longestDistance.length; i++)
            if (longestDistance[i] > max && i != startingVertex) {
                max = longestDistance[i];
                endPoint = i;
            }

        if (max > maxCommon) {
            maxCommon = max;
            builder = new StringBuilder("");
            builder = buildPath(longestDistance, endPoint, endPoint, builder);
        }
    }

    public StringBuilder buildPath(int[] longestDistance, int endPoint, int trueEnd, StringBuilder builder) {
        if (endPoint == startingVertex) {
            builder.append(words[startingVertex]).append(" ");
            return builder;
        }
        //find back-edge of highest length
        int nextDistance = -1;
        int next = startingVertex;
        for (int i = 0; i < graph.size(); i++)
            if (graph.get(i).contains(endPoint) && longestDistance[i] > nextDistance) {
                if (longestDistance[i] == 0 && endPoint != trueEnd) {
                    continue;
                }
                nextDistance = longestDistance[i];
                next = i;
            }
        buildPath(longestDistance, next, trueEnd, builder);
        builder.append(words[endPoint]).append(" ");
        return builder;
    }

    //Topological sort based on depth-first search
    public void depthFirstSearch(int start) {
        discovered[start] = true;
        for (int node : graph.get(start)) {
            if (!discovered[node]) {            //if new child, add to queue
                parent[node] = start;            //remember parents, for paths
                depthFirstSearch(node);
            }
        }
        sort.push(start);
    }

    //Sparse matrix implementation of a graph
    public void insertEdge(int from, int to) {
        if (!graph.containsKey(from))
            graph.put(from, new TreeSet<Integer>());
        if (!graph.containsKey(to))
            graph.put(to, new TreeSet<Integer>());
        graph.get(from).add(to);
    }
}