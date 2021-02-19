package ru.geekbrains.homework_2_1.barriers;

import ru.geekbrains.homework_2_1.participants.Actions;

public class Wall implements Shiftable {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean shifting(Actions act) {
        return act.climb(height);
    }
}
