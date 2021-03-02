package ru.geekbrains.homework_6;

public abstract class Animal {

    private String name;
    static int counter = 0;

    public Animal (String name) {
        this.name = name;
        counter++;

    }

    public void animalCounter () {
        System.out.println("Количество созданных котов и собак = " + counter);
    }




    public String getName () {
        return name;
    }


    public abstract void swim(int distance);

    public abstract void run(int distance);





}


