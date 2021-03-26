package ru.geekbrains.homework_2_1.participants;

public class Robot implements Actions{

    private String name;
    private int maxRun;
    private int maxClimb;

    public Robot(String name, int maxRun, int maxClimb) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxClimb = maxClimb;
    }

    @Override
    public boolean run(int distance) {
        if (distance > maxRun) {
            System.out.printf(" Robot %s didn't pass the distance for %d metres \n", name, distance);
            return false;
        } else {
            System.out.printf(" Robot %s passed the distance for %d metres \n", name, distance);
            return true;
        }
    }

        @Override
        public boolean climb(int height) {
            if (height > maxClimb) {
                System.out.printf(" Robot %s didn't pass the wall for %d metres \n", name,height);
                return false;
            } else {
                System.out.printf(" Robot %s passed the wall for %d metres \n", name,height);
                return true;
            }
        }
}
