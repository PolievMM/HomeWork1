package ru.geekbrains.homework3_5;


import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{

    private Semaphore semaph;

    public Tunnel () {
        semaph = new Semaphore(Main.CARS_COUNT / 2);
        this.length = 80;
        this.description = "Tunnel is " + length + " metres";
    }

    @Override
    public void go(Car c) {
        try {
            if (!semaph.tryAcquire()) {
                System.out.println(c.getName() + " waiting for starting stage: " + description);
                semaph.acquire();
            }
            System.out.println(c.getName() + " started stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " ended stage: " + description);
            semaph.release();
        }
    }
}
