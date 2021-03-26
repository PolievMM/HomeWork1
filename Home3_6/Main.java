package ru.geekbrains.homework3_6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


    }

    public static int[] masWithFour(int[] mas) {
        for (int i = mas.length - 1; i >= 0; i--) {
            if (mas[i] == 4) {
                return Arrays.copyOfRange(mas, i + 1, mas.length);
            }

        }
        throw new RuntimeException("There is no 4 in array");
    }

    public static boolean oneAndFourMas(int[] mas) {
        boolean isOne = false;
        boolean isFour = false;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] != 1 && mas[i] != 4) {
                return false;
            }
            if (mas[i] == 1) {
                isOne = true;
            }
            if (mas[i] == 4) {
                isFour = true;
            }

        }
        return isFour && isOne;

    }

    public static int add(int a, int b) {
        return a + b;
    }
}
