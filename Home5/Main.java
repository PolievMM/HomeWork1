package ru.geekbrains.home_work_5;

public class Main {

    public static void main(String[] args) {

        Employee[] persons = new Employee[5];
        persons[0] = new Employee("Александр Сергеевич Пушкин", "Ведущий поэт", "pushkin_as@lit.com",
                "7-916-987-96-11", 150000, 25);
        persons[1] = new Employee("Гавриил Романович Державин", "Редактор", "derjavin_gr@lit.com",
                "7-905-878-57-01", 100000, 48);
        persons[2] = new Employee("Михаил Васильевич Ломоносов", "Главный редактор", "lomonosov_mv@lit.com",
                "7-999-777-77-77", 250000, 50);
        persons[3] = new Employee("Михаил Юрьевич Лермонтов", "Поэт", "lermontov_mu@lit.com",
                "7-916-500-18-30", 500000, 20);
        persons[4] = new Employee("Алексей Андреевич Аракчеев", "Цензор", "аrakcheev_aa@lit.com",
                "7-909-119-90-80", 200000, 60);




        for (Employee i: persons) {

            if (i.getAge() > 40)
            i.toSeeInfo();
        }



    }


}
