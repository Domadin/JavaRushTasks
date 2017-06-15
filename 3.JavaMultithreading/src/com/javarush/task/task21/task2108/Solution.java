package com.javarush.task.task21.task2108;

import java.util.Arrays;

/*
Клонирование растений
*/
public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println();
        System.out.println("Проверка имен");
        System.out.println(tree.getName().hashCode());
        System.out.println(clone.getName().hashCode());
        System.out.println();
        System.out.println("Проверка веток");
        System.out.println(tree.branches);
        System.out.println(clone.branches);
        System.out.println();
        System.out.println("Изменение имени (Верно, если не TestClass)");
        tree.setName("TestClass");
        System.out.println(clone.getName());
        System.out.println();
        System.out.println("Проверка веток (Верно, если не TestClass)");
        tree.branches[0] = "TestClass";
        System.out.println(clone.branches[0]);
    }

    public static class Plant {
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Tree extends Plant implements Cloneable {
        private String[] branches;

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches() {
            return branches;
        }

        @Override
        protected Tree clone() throws CloneNotSupportedException {
            //return new Tree(getName(), getBranches().clone());
            Tree clone = (Tree) super.clone();
            clone.branches = clone.branches.clone();
            return clone;
        }
    }
}
