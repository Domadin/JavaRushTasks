package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(Arrays.asList(
                new Horse("Horse1", 3, 0),
                new Horse("Horse2", 3, 0),
                new Horse("Horse3", 3, 0)));
        game.run();
        game.printWinner(game.getWinner());
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse : horses) {
            if (winner.getDistance() < horse.getDistance()) {
                winner = horse;
            }
        }
        return winner;
    }

    /*    public void printWinner() {
            Horse winner = horses.get(0);
            for (Horse horse : horses) {
                if (winner.getDistance() < horse.getDistance()) {
                    winner = horse;
                }
            }
            System.out.println(String.format("Winner is %s!", winner.getName()));
        }*/
    public void printWinner(Horse winner) {
        System.out.println(String.format("Winner is %s!", winner.getName()));
    }
}
