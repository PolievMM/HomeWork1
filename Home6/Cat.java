package ru.geekbrains.homework_6;

public class Cat extends Animal {



    public Cat (String name) {
        super(name);

    }

    @Override
    public void swim(int distance) {

        System.out.println("Котьки не могут плавать.");

    }

    @Override
    public void run(int distance) {

        if (distance > 200)
            System.out.println("Котейки не могут пробежать больше 200 метров.");
        else if (distance < 0)
            System.out.println("Животные не могут пробежать отрицательную дистанцию.");
        else
            System.out.println("Котька " + getName() + " пробежала " + distance + " метров.");
    }




}

