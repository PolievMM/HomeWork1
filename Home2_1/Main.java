package ru.geekbrains.homework_2_1.participants;

import ru.geekbrains.homework_2_1.barriers.Racetrack;
import ru.geekbrains.homework_2_1.barriers.Shiftable;
import ru.geekbrains.homework_2_1.barriers.Wall;

public class Main {

    public static void main(String[] args) {

        Actions [] participants = {
                new Cat("Yusha", 50, 20),
                new Human("Steve", 100, 20),
                new Robot("Bender", 1000, 500),
        };

        Shiftable [] barrier = {
          new Racetrack(100),
          new Wall(20),
        };

        for (Actions act : participants) {
            for (Shiftable shift : barrier) {
                if (!shift.shifting (act)) {
                    break;
                }
            }
        }
    }
}
