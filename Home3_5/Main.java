package ru.geekbrains.homework3_5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {


    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        System.out.println("Important declaration >>> preparation!");

        CyclicBarrier barrier = new CyclicBarrier(Main.CARS_COUNT + 1);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car [] cars  = new Car [CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier);
            new Thread(cars[i]).start();
            
        }

        try {
            barrier.await();
            System.out.println("Important declaration >>> race's started!");
            barrier.await();
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Important declaration >>> race is over!");




    }
}
