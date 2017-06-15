package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            String[] wheelNames = loadWheelNamesFromDB();
            if (Wheel.values().length != wheelNames.length) {
                throw new IllegalArgumentException();
            }
            //wheels = new ArrayList<>(Arrays.asList(Wheel.values()));
            wheels = new ArrayList<>();
            for (String s : wheelNames) {
                wheels.add(Wheel.valueOf(s));
            }

        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();

    }
}
