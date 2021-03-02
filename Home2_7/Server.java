package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private static final int PORT = 5353;
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private List<ClientHandler> client;
    private AuthService authService;

    public Server() {

        client = new CopyOnWriteArrayList<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started successfully");


            while (true) {


                socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);


            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void broadcastMsg (ClientHandler sender, String msg) {
        String message = String.format("[ %s ]: %s", sender.getNickname(), msg);
        for (ClientHandler clientHandler : client) {
            clientHandler.sendMsg(message);
        }
    }

    public void personalMsg (ClientHandler sender, String recipient, String msg) {
        String message = String.format("[ %s ] writes to [ %s ]: %s", sender.getNickname(), recipient, msg);
        for (ClientHandler clientHandler : client) {
            if (clientHandler.getNickname().equals(recipient)) {
                clientHandler.sendMsg(message);
                if (!clientHandler.equals(sender)) {
                    sender.sendMsg(message);
                }
                return;
            }

        }
        sender.sendMsg("User not found: " + recipient);

    }

    public void subscribe (ClientHandler clientHandler) {
        client.add(clientHandler);
    }

    public void unsubscribe (ClientHandler clientHandler) {
        client.remove(clientHandler);
    }

    public AuthService getAuthService () {
        return authService;
    }
}
