package ru.geekbrains.homework2_2;

public class Main {

    static int SIZE = 4;

    public static void main(String[] args) {

        String [][] masName = {
                {"1", "2", "3", "4"},
                {"10", "20", "30", "40"},
                {"100", "200", "300", "400"},
                {"1000", "2000", "rr", "4000"},

        };

        try {
            System.out.println(myMas(masName));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println("Exception line = " + e.getLines() + " Exception column = " + e.getColumns());
            System.out.println(masName [e.getLines()][e.getColumns()]);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
        System.out.println("Programm's end");



    }

    public static int myMas (String [][] masName) throws MyArraySizeException, MyArrayDataException {

        if (masName.length != SIZE) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < masName.length; i++) {
            if (masName[i].length != SIZE) {
                throw new MyArraySizeException();
            }
        }

        int sum = 0;

        for (int i = 0; i < masName.length; i++) {
            for (int j = 0; j < masName[i].length; j++) {
                try {
                    sum += Integer.parseInt(masName[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Exceptions found: line/ column = " + i + " " + j, i, j);
                }
            }
        }
        return sum;

        }
}
