package ru.geekbrains.homework3_5;

public class Road extends Stage{

    public Road(int length) {
        this.length = length;
        this.description = "Road is " + length + " metres";
    }


    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " began stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " ended stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
