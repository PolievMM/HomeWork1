package ru.geekbrains.homework7;

public class Cat {

    private String name;
    private int appetite;
    private boolean catFull;


    public Cat(String name, int appetite) {

        this.name = name;
        this.appetite = appetite;
        this.catFull = catFull;
    }

    public int getAppetite() {
        return appetite;
    }

    public String getName() {
        return name;
    }

    public boolean getCatFull() {
        return catFull;
    }

    public void eat(Plate type) {

        type.decreaseFood(appetite);


    }

}


