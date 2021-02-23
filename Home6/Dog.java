package ru.geekbrains.homework_6;

public class Dog extends Animal {

    public Dog (String name) {
        super(name);

    }

    @Override
    public void swim(int distance) {

        if (distance > 10)
            System.out.println("Собаки не могут проплыть больше 10 метров.");
        else if (distance < 0)
            System.out.println("Животные не могут проплыть отрицательную дистанцию.");
        else
            System.out.printf("Собака %s проплыла %d метров \n", getName(),distance);
    }

    @Override
    public void run(int distance) {
        if (distance > 500)
            System.out.println("Собака не может пробежать больше полукилометра.");
        else if (distance < 0)
            System.out.println("Животные не могут пробежать отрицательную дистанцию.");
        else
            System.out.printf("Собака %s пробежала %d метров. \n", getName(),distance);

    }


}

