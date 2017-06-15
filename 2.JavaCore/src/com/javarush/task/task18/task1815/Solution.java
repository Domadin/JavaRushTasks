package com.javarush.task.task18.task1815;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class Solution {
    public interface ATableInterface {
        void setModel(List<Integer> rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);

        int getListElement(int id);
    }

    public class TableInterfaceWrapper  {
        private ATableInterface aTableInterface;

        public TableInterfaceWrapper(ATableInterface aTableInterface) {
            this.aTableInterface = aTableInterface;
        }

        public void setModel(List<Integer> rows) {
            System.out.println(rows.size());
            aTableInterface.setModel(rows);
        }

        public String getHeaderText() {
            return aTableInterface.getHeaderText().toUpperCase();
        }

        public void setHeaderText(String newHeaderText) {
            aTableInterface.setHeaderText(newHeaderText);
        }

        public int getListElement(int id) {
            return aTableInterface.getListElement(id);
        }
    }

    public class TableTest implements ATableInterface {
        String header;
        List<Integer> rows;

        public TableTest() {
            rows = new ArrayList<>(1);
            rows.add(5);
            header = "Test";
        }

        @Override
        public void setModel(List<Integer> rows) {
            this.rows = rows;
        }

        @Override
        public String getHeaderText() {
            return this.header;
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            this.header = newHeaderText;
        }

        @Override
        public int getListElement(int id) {
            return rows.get(id);
        }
    }


    public static void main(String[] args) {
        new Solution().starter();
    }

    public void starter() {
        TableInterfaceWrapper table = new TableInterfaceWrapper(new TableTest());
        table.setModel(asList(5, 6, 4, 5));
        table.setHeaderText("Привет");
        System.out.println(table.getHeaderText());
        System.out.println(table.getListElement(1));
    }
}