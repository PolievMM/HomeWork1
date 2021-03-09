package ru.geekbrains.homework_6;

public class Main {

// 1. Создать классы Собака и Кот с наследованием от класса Животное.
// 2. Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
// Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
// 3. У каждого животного есть ограничения на действия
// (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
// 4. * Добавить подсчет созданных котов, собак и животных.
    public static void main(String[] args) {

       Cat cat = new Cat("Юша");
       Dog dog = new Dog("Амира");
       Dog dog2 = new Dog("Сафир");
       Cat cat2 = new Cat("Муша");
       Cat cat3 = new Cat ("Игорь");


       cat.run(100);
       cat.swim(20);
       dog.run(300);
       dog.swim(15);
       System.out.println();
       cat3.animalCounter();


    }
}
