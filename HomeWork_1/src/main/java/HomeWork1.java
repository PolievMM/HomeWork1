import java.util.Scanner;

public class HomeWork1 {
    public static void main(String[] args) {
    // 2. Создать переменные всех пройденных типов данных и инициализировать их значения
    byte a = 1;
    short b = 2;
    int c = 3;
    long d = 4L;
    float e = 5f;
    double f = 6;
    char g = '#';
    boolean h = true;
    String line = "Java is the best language";
    // Итог упражнения 3
    System.out.println(exercise3(3.5f, 5.7f, 10.15f, 5.15f));
    // Итог упражнения 4
    System.out.println(exercise4(10, 12));
    // Итог упражнения 5
    exercise5(10);
    // Итог упражнения 6
    System.out.println(exercise6(0));
    // Итог упражнения 7
    exercise7("Поликарп");
    // Итог упражнения 8
    exersice8(2100);
    }

    // 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    //где a, b, c, d – аргументы этого метода, имеющие тип float.
    public static float exercise3 (float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    // 4. Написать метод, принимающий на вход два целых числа и проверяющий,
    // что их сумма лежит в пределах от 10 до 20 (включительно),
    // если да – вернуть true, в противном случае – false.
    public static boolean exercise4 (int a, int b) {
        if (a + b>=10 && a + b <= 20) {
        return true;
        }
        else {
        return false;
        }
    }

    // 5. Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    // Замечание: ноль считаем положительным числом
    public static void exercise5 (int a) {
        if (a >= 0) {
        System.out.println("Ваше число положительное");
        }
        else {
        System.out.println("Ваше число отрицательное");
        }
    }

    // 6. Написать метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
    public static boolean exercise6 (int a) {
        if (a < 0) {
        return true;
        }
        else {
        return false;
        }
    }

    // 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя. Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
    public static void exercise7 (String who) {
        System.out.println("Привет, " + who + "!");
    }

    // 8. Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный

    public static void exersice8 (int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            System.out.println(year + " – високосный год!");
        }
            else if (year % 4 == 0 && year % 400 == 0){
                System.out.println(year + " – високосный год!");
            }

            else {
            System.out.println(year + " – невисокосный год!");
        }

        }

    }
