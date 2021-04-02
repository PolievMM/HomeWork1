package ru.geekbrains.home_work_4;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final char EMPTY_POINT = '.';
    private static final char X_POINT = 'X';
    private static final char O_POINT = '0';
    private static final int SQUARE = 3;
    private static char[][] field;
    private static Scanner scan = new Scanner(System.in);
    private static ThreadLocalRandom ran = ThreadLocalRandom.current();


    public static void main(String[] args) {
        fillField();
        printField();
        while (true) {
            manMove();
            printField();
            if (winCondition(X_POINT)) {
                System.out.println("Вы победили!");
                break;
            }
            if (fieldFulness()) {
                System.out.println("Победила дружба!");
            break;
            }
            aiMove();
            printField();
            if (winCondition(O_POINT)) {
                System.out.println("Победил компьютер!");
                break;
            }
            if (fieldFulness()) {
                System.out.println("Победила дружба!");
                break;
            }

        }
        System.out.println("Игра окончена!");


    }

    private static void fillField() {
        field = new char[SQUARE][SQUARE];
        for (int i = 0; i < SQUARE; i++) {
            for (int j = 0; j < SQUARE; j++) {
                field[i][j] = EMPTY_POINT;
            }
        }
    }

    private static void printField() {
        System.out.println();
        for (int i = 0; i < SQUARE; i++) {
            for (int j = 0; j < SQUARE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void manMove() {
        int x = -1;
        int y = -1;
        do {
            try {

                System.out.println("Введите через пробел координаты X и Y от 1 до 3");
                x = scan.nextInt() - 1;
                y = scan.nextInt() - 1;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Вводите только числа!");
                scan = new Scanner(System.in);
            }
        } while (!checkNum(x, y));
        field[x][y] = X_POINT;

    }

    private static void aiMove() {
        int x = -1;
        int y = -1;
        System.out.println("Очередь компьютера");
        do {

            x = ran.nextInt(0,3);
            y = ran.nextInt(0, 3);

        } while (!checkNum(x, y));
        field[x][y] = O_POINT;
        System.out.println(y);
        System.out.println(x);
    }

    private static boolean checkNum(int x, int y) {
        return x >= 0 && x < SQUARE
                && y >= 0 && y < SQUARE
                && field[x][y] == EMPTY_POINT;

    }
    //Проверка корректности координат: должны входить в размеры поля и быть пустыми, то есть заполнены точкой
    private static boolean fieldFulness () {
        for (int i = 0; i < SQUARE; i++) {
            for (int j = 0; j < SQUARE; j++) {
                if (field[i][j] == EMPTY_POINT)
                    return false;


            }
        }
        return true;
    }

    private static boolean winCondition (char winOrNot) {
        if (field[0][0] == winOrNot && field[0][1] == winOrNot && field[0][2] == winOrNot)
            return true;
        if (field[1][0] == winOrNot && field[1][1] == winOrNot && field[1][2] == winOrNot)
            return true;
        if (field[2][0] == winOrNot && field[2][1] == winOrNot && field[2][2] == winOrNot)
            return true;
        if (field[0][0] == winOrNot && field[1][0] == winOrNot && field[2][0] == winOrNot)
            return true;
        if (field[0][1] == winOrNot && field[1][1] == winOrNot && field[2][1] == winOrNot)
            return true;
        if (field[0][2] == winOrNot && field[1][2] == winOrNot && field[2][2] == winOrNot)
            return true;
        if (field[0][0] == winOrNot && field[1][1] == winOrNot && field[2][2] == winOrNot)
            return true;
        if (field[2][0] == winOrNot && field[1][1] == winOrNot && field[0][2] == winOrNot)
            return true;

        return false;
    }

}
