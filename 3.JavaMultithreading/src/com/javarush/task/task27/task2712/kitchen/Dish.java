package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        StringBuilder builder = new StringBuilder();
        for (Dish dish : Dish.class.getEnumConstants()) {
            builder.append(dish.toString()).append(" ");
        }
        return builder.toString().trim();
    }
}
