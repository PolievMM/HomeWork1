package ru.geekbrains.homework7;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Cat[] homeCat = {
                new Cat("Юша", 15),
                new Cat("Муша", 10),
                new Cat("Сафир", 30),
                new Cat("Амира", 5),
        };

        System.out.println("Добавьте корма в миску");
        Plate catPlate = new Plate(new Scanner(System.in).nextInt());

        Scanner scanner = new Scanner(System.in);
        int put;
        do {

            for (Cat myAnimals : homeCat) {

                if (!myAnimals.getCatFull() && myAnimals.getAppetite() <= catPlate.getFood()) {
                    myAnimals.eat(catPlate);
                    System.out.printf("Кошка %s поела и сыта \n", myAnimals.getName());
                } else {
                    System.out.printf("Кошке %s не хватило еды \n", myAnimals.getName());
                }
            }
            catPlate.plateInfo();

            System.out.println("Чтобы добавить корма в миску нажмите 1, затем Enter и укажите количество еды.\n" +
                    "Чтобы прекратить кормить зверей, нажмите 2. ");
            put = scanner.nextInt();
            if (put == 1)
                catPlate.addFood();
        } while (put != 2);


    }
}
