package ru.geekbrains.homework2_2;

public class MyArrayDataException extends RuntimeException {

    private int columns;
    private int lines;

    public MyArrayDataException(String message, int lines, int columns) {
        super(message);
        this.lines = lines;
        this.columns = columns;

    }

    public int getColumns() {
        return columns;
    }

    public int getLines() {
        return lines;
    }
}
