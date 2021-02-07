package ru.geekbrains.homework_2_1.barriers;

import ru.geekbrains.homework_2_1.participants.Actions;

public class Racetrack implements Shiftable {

    private int distance;

    public Racetrack(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean shifting(Actions act) {
        return act.run(distance);
    }
}
