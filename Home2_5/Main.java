package ru.geekbrains.homework2_5;

import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    public static void main(String[] args) {

        float [] mas = new float[SIZE];
        for (float filling : mas) {
            filling = 1;

        }
        form1(mas);
        form2(mas);


    }


    public static void form1 (float [] mas) {

        long time = System.currentTimeMillis();
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (float)(mas[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long stopTime = System.currentTimeMillis();

        System.out.println("Время выполнения операции первой формулы: " + (stopTime - time));

        System.out.println("Начало второй формулы");


    }

    public static void form2 (float [] mas) {
        long time = System.currentTimeMillis();
        float [] var1 = new float[HALF];
        float [] var2 = new float[HALF];
        System.arraycopy(mas, 0, var1, 0, HALF);
        System.arraycopy(mas, HALF, var2, 0, HALF);

        Thread firstLine = new Thread(() -> {
            for (int i = 0; i < var1.length; i++) {
                var1[i] = (float)(var1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread secondLine = new Thread(() -> {
            for (int i = 0; i < var2.length; i++) {
                var2[i] = (float)(var2[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        });

        firstLine.start();
        secondLine.start();

        try {
            firstLine.join();
            secondLine.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(var1, 0, mas, 0, HALF);
        System.arraycopy(var2, 0, mas, HALF, HALF);

        long stopTime = System.currentTimeMillis();
        System.out.println();

        System.out.println("Время выполнения операции второй формулы: " + (stopTime - time));

        System.out.println("Конец");




    }
}
