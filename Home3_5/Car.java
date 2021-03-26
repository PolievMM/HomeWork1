package ru.geekbrains.homework3_5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private static AtomicInteger atomInt;
    private static int CARS_COUNT;

    static {
        atomInt = new AtomicInteger();
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier barrier;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Participant # " + CARS_COUNT;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            System.out.println(this.name + " preparing");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " ready");
            barrier.await();
            barrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);

            }

            if (atomInt.incrementAndGet() == 1) {
                System.out.println(name + " wins!");
            }
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
