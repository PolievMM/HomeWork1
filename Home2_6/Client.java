package ru.geekbrains.homework2_6;

import javafx.scene.chart.ScatterChart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 3702;


    public static void main(String[] args) {
        Socket socket = null;
        Scanner scan = new Scanner(System.in);


        try {
            socket = new Socket(SERVER_ADRESS, SERVER_PORT);
            System.out.println("Подключен к серверу " + socket.getRemoteSocketAddress());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            Thread threadReader = new Thread(() -> {
                try {
                    while (true) {
                        outputStream.writeUTF(scan.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadReader.setDaemon(true);
            threadReader.start();

            while (true) {
                String str = inputStream.readUTF();
                if (str.equals("/close")) {
                    System.out.println("Потеряно соединение с сервером");
                    outputStream.writeUTF("/close");
                    break;
                } else {
                    System.out.println("Сервер: " + str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
