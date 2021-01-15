package ru.geekbrains.home_work_5;

public class Employee {

    private String name;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;


    public Employee (String name, String position, String email, String phoneNumber, int salary, int age) {

       this.name = name;
       this.position = position;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.salary = salary;
       this.age = age;

    }

    public int getAge() {

        return age;
    }

public void toSeeInfo () {
        System.out.println("Сотрудник ОАО \"Солнце русской литературы\"");
        System.out.println("ФИО: " + name);
        System.out.println("Должность: " + position);
        System.out.println("E-mail: " + email);
        System.out.println("Телефон: " + phoneNumber);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();
    }

}
