package ru.geekbrains.homework3_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        String []mas = {"One", "Two", "Three", "Four", "Five"};
        System.out.println("Написать метод, который преобразует массив в ArrayList");
        ArrayList <String> masList1 = masList(mas);
        System.out.println(masList1);

        System.out.println("Написать метод, который меняет два элемента массива местами");
        change(mas, 0, 2);
        System.out.println(Arrays.toString(mas));

        System.out.println("Задача с фруктами и коробками");
        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        box1.add(new Apple());
        System.out.println(box1.getWeight());
        box1.remove(box2);
        System.out.println(box2.getWeight());
        System.out.println(box1.getWeight());

    }


    public static <T> ArrayList <T> masList (T[] mas) {
        return new ArrayList<>(Arrays.asList(mas));
    }


    public static void change (Object [] mas, int index1, int index2) {
        Object num1 = mas[index1];
        mas[index1] = mas[index2];
        mas[index2] = num1;
    }


}
