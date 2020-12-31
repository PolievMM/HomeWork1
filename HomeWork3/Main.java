package ru.geek.brains.home_work_3;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);


// 1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).


    public static void main(String[] args) {
        guessGame();

    }


    public static void guessGame() {
        System.out.println("Угадайте число от 0 до 9. У вас три попытки!");
        Random secretNum = new Random();
        int guessNum = secretNum.nextInt(10);
        int move = scan.nextInt();
        int tryGuess = 3;
        while (move != guessNum && tryGuess != 1) {
            if (move > guessNum) {
                System.out.println("Загаданное число меньше");
                tryGuess--;
                move = scan.nextInt();
            } else if (move < guessNum) {
                System.out.println("Загаданное число больше");
                tryGuess--;
                move = scan.nextInt();
            }
            if (tryGuess == 1 && move != guessNum) {
                System.out.println("Сожалеем, вы проиграли =(");
            }
        }

        if (move == guessNum) {
            System.out.println("Вы выиграли! Поздравляем!");
        }
        System.out.println("Повторить игру? 1 - да, 2 - нет");
        repeat();
    }


    public static void repeat() {
        int rep = scan.nextInt();
        if (rep == 1) {
            guessGame();
        } else
            System.out.println("Ждем вас в следующий раз!");

    }


}