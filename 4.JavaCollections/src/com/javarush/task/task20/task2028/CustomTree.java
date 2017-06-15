package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    private Entry<String> root = new Entry<>("root");

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        boolean isAvailableToAddLeftChildren() {
            return leftChild == null;
        }

        boolean isAvailableToAddRightChildren() {
            return rightChild == null;
        }

        boolean isLeftChildrenExists() {
            return leftChild != null;
        }

        boolean isRightChildrenExists() {
            return rightChild != null;
        }
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list.size());
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public int size() {
        Entry<String> currentEntry;
        int size = 0;

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            currentEntry = queue.poll();
            if (currentEntry.isLeftChildrenExists()) {
                size++;
                queue.add(currentEntry.leftChild);
            }
            if (currentEntry.isRightChildrenExists()) {
                size++;
                queue.add(currentEntry.rightChild);
            }
        }
        return size;
    }

    @Override
    public boolean add(String elementName) {
        Entry<String> currentEntry;
        Entry<String> entryToAdd = new Entry<>(elementName);

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            currentEntry = queue.poll();
            if (currentEntry.isAvailableToAddLeftChildren()) {
                entryToAdd.parent = currentEntry;
                currentEntry.leftChild = entryToAdd;
                return true;
            }
            if (currentEntry.isAvailableToAddRightChildren()) {
                entryToAdd.parent = currentEntry;
                currentEntry.rightChild = entryToAdd;
                return true;
            }
            queue.add(currentEntry.leftChild);
            queue.add(currentEntry.rightChild);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return this.remove(String.valueOf(o));
    }

    public boolean remove(String elementName) {
        Entry<String> currentEntry;

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            currentEntry = queue.poll();
            if (currentEntry.isLeftChildrenExists())
                if (currentEntry.leftChild.elementName.equals(elementName)) {
                    currentEntry.leftChild = null;
                    return true;
                }
            if (currentEntry.isRightChildrenExists())
                if (currentEntry.rightChild.elementName.equals(elementName)) {
                    currentEntry.rightChild = null;
                    return true;
                }
            queue.add(currentEntry.leftChild);
            queue.add(currentEntry.rightChild);
        }
        return false;
    }

    public String getParent(String childName) {
        Entry<String> currentEntry;

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            currentEntry = queue.poll();
            if (currentEntry.isLeftChildrenExists())
                if (currentEntry.leftChild.elementName.equals(childName))
                    return currentEntry.elementName;
                else queue.add(currentEntry.leftChild);

            if (currentEntry.isRightChildrenExists())
                if (currentEntry.rightChild.elementName.equals(childName))
                    return currentEntry.elementName;
                else queue.add(currentEntry.rightChild);
        }
        return null;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
