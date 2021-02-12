package ru.geekbrains.homework7;

import java.util.Scanner;

public class Plate {

    private int food;


    public Plate(int food) {

        this.food = food;

    }

    public int getFood() {
        return food;
    }

    boolean decreaseFood(int wasted) {
        if (wasted > food) {
            System.out.println("Кошка пыталась поесть больше, чем есть в тарелке");
            return false;

        } else food -= wasted;
        return true;
    }

    public void addFood() {
        Scanner scan = new Scanner(System.in);
        this.food = scan.nextInt() + food;
        plateInfo();
    }

    public void plateInfo() {
        System.out.println("Количество еды в тарелке: " + food);
    }
}
