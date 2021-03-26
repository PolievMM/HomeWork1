package ru.geekbrains.home_work_4;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



public class Main {

//    0. Объявление переменных
    private static final char X_POINT = 'X';
    private static final char O_POINT = '0';
    private static final char FREE_PLACE = '.';
    private static final int fieldFormat1 = 3;
    private static final int fieldFormat2 = 5;
    private static char[][] field;
    private static Scanner scan = new Scanner(System.in);
    private static ThreadLocalRandom ran = ThreadLocalRandom.current();

//    1. Метод main с выбором размера игрового поля
    public static void main(String[] args) {

        System.out.println("Выбирайте игровое поле: 1 - 3х3, 2 - 5х5.");
        int choose = scan.nextInt();
        if (choose == 1) {
            game1();
        }
        if (choose == 2) {
            game2();
        }
    }

//    2. Логика игры для поля 3х3

    private static void game1 () {
        format1();
        fillField();
        while (true) {
            manMove();
            fillField();
            if (winCondition(X_POINT)) {
                System.out.println("Вы победили!");
                break;
            }
            if (fieldFulness()) {
                System.out.println("Победила дружба!");
                break;
            }
            aiMove();
            fillField();
            if (winCondition((O_POINT))) {
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

//    3. Логика игры для поля 5х5
    private static void game2 () {
        format2();
        fillField2();
        while (true) {
            manMove2();
            fillField2();
            if (winCondition2(X_POINT)) {
                System.out.println("Вы победили!");
                break;
            }
            if (fieldFulness2()) {
                System.out.println("Победила дружба!");
                break;
            }
            aiMove2();
            fillField2();
            if (winCondition2(O_POINT)) {
                System.out.println("Победил компьютер!");
                break;
            }
            if (fieldFulness2()) {
                System.out.println("Победила дружба!");
                break;
            }


        }
        System.out.println("Игра окончена!");
    }


//    4. Инициализация поля 3х3
    private static void format1() {
        field = new char[fieldFormat1][fieldFormat1];
        for (int i = 0; i < fieldFormat1; i++) {
            for (int j = 0; j < fieldFormat1; j++) {
                field[i][j] = FREE_PLACE;
            }
        }
    }

//    5. Инициализация поля 5х5
    private static void format2() {
        field = new char[fieldFormat2][fieldFormat2];
        for (int i = 0; i < fieldFormat2; i++) {
            for (int j = 0; j < fieldFormat2; j++) {
                field[i][j] = FREE_PLACE;
            }
        }
    }


//    6. Заполнение поля 3х3
    private static void fillField() {
        System.out.println();
        for (int i = 0; i < fieldFormat1; i++) {
            for (int j = 0; j < fieldFormat1; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();

        }
    }

//    7. Заполнение поля 5х5
    private static void fillField2 () {
        System.out.println();
        for (int i = 0; i < fieldFormat2; i++) {
            for (int j = 0; j < fieldFormat2; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();

        }
    }

//    8. Ход человека для поля 3х3
    private static void manMove() {
        int human_x = -1;
        int human_y = -1;
        do {
            try {
                System.out.println("Введите через пробел координат X и Y от 1 до 3, где X - номер строки, а Y - номер столбца");
                human_x = scan.nextInt() - 1;
                human_y = scan.nextInt() - 1;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Вводите только числа! ");
                scan = new Scanner(System.in);
            }
        } while (!checkMove(human_x, human_y));
        field[human_x][human_y] = X_POINT;

    }

//    9. Ход человека для поля 5х5
    private static void manMove2() {
        int human_x = -1;
        int human_y = -1;
        do {
            try {
                System.out.println("Введите через пробел координат X и Y от 1 до 5, где X - номер строки, а Y - номер столбца");
                human_x = scan.nextInt() - 1;
                human_y = scan.nextInt() - 1;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Вводите только числа! ");
                scan = new Scanner(System.in);
            }
        } while (!checkMove2(human_x, human_y));
        field[human_x][human_y] = X_POINT;

    }

//    10. Ход компьютера для поля 3х3
    private static void aiMove() {
        int ai_x = -1;
        int ai_y = -1;
        System.out.println("Ход компьютера");
        do {
            ai_x = ThreadLocalRandom.current().nextInt(0, 3);
            ai_y = ThreadLocalRandom.current().nextInt(0, 3);
        } while (!checkMove(ai_x, ai_y));
        field[ai_x][ai_y] = O_POINT;
        System.out.println(ai_x);
        System.out.println(ai_y);
    }

//    11. Ход компьютера для поля 5х5
    private static void aiMove2() {
        int ai_x = -1;
        int ai_y = -1;
        System.out.println("Ход компьютера");
        do {
            ai_x = ThreadLocalRandom.current().nextInt(0, 5);
            ai_y = ThreadLocalRandom.current().nextInt(0, 5);
        } while (!checkMove2(ai_x, ai_y));
        field[ai_x][ai_y] = O_POINT;
        System.out.println(ai_x);
        System.out.println(ai_y);
    }

//    12. Проверка хода на вхождение в границы и незанятость для поля 3х3
    private static boolean checkMove(int x, int y) {
        return x >= 0 && x < fieldFormat1
                && y >= 0 && y < fieldFormat1
                && field[x][y] == FREE_PLACE;
    }

//    13. Проверка хода на вхождение в границы и незанятость для поля 5х5
    private static boolean checkMove2(int x, int y) {
        return x >= 0 && x < fieldFormat2
                && y >= 0 && y < fieldFormat2
                && field[x][y] == FREE_PLACE;
    }

//    14. Проверка на ничью для поля 3х3
    private static boolean fieldFulness() {
        for (int i = 0; i < fieldFormat1; i++) {
            for (int j = 0; j < fieldFormat1; j++) {
                if (field[i][j] == FREE_PLACE) {
                    return false;
                }
            }
        }
        return true;
    }

//    15. Проверка на ничью для поля 5х5
    private static boolean fieldFulness2() {
        for (int i = 0; i < fieldFormat2; i++) {
            for (int j = 0; j < fieldFormat2; j++) {
                if (field[i][j] == FREE_PLACE) {
                    return false;
                }
            }
        }
        return true;
    }

//    16. Проверка на победу для поля 3х3
    private static boolean winCondition(char winOrNot) {
        int lines = 0;
        int columns = 0;
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < fieldFormat1; i++) {
            if (field[i][i] == winOrNot) {
                diag1++;

                if (diag1 == 3)
                    return true;
            }
            if (field[i][fieldFormat1 - i - 1] == winOrNot) {
                diag2++;

                if (diag2 == 3)
                    return true;
            }
            for (int j = 0; j < fieldFormat1; j++) {
                if (field[i][j] == winOrNot) {
                    lines++;

                    if (lines == 3)
                        return true;
                }

                if (field[j][i] == winOrNot) {
                    columns++;

                    if (columns == 3)
                        return true;
                }

            }
            lines = 0;
            columns = 0;
        }
        return false;

    }

//    17. Проверка на победу для поля 5х5 (для малых диагоналей я не сумел найти решение, только для больших)
    private static boolean winCondition2(char winOrNot2) {
        int lines = 0;
        int columns = 0;
        int diag1 = 0;
        int diag2 = 0;


        for (int i = 0; i < fieldFormat2; i++) {
            if (field[i][i] == winOrNot2) {
                diag1++;

                if (diag1 == 4)
                    return true;
            }
            if (field[i][fieldFormat2 - i - 1] == winOrNot2) {
                diag2++;

                if (diag2 == 4)
                    return true;
            }

            for (int j = 0; j < fieldFormat2; j++) {
                if (field[i][j] == winOrNot2) {
                    lines++;

                    if (lines == 4)
                        return true;
                }

                if (field[j][i] == winOrNot2) {
                    columns++;

                    }
                    if (columns == 4)
                        return true;
                if (field[i][j] != winOrNot2)
                    lines = 0;
                if (field[j][i] != winOrNot2)
                    columns = 0;
                }


            }
        return false;


    }



}
