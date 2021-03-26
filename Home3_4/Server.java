package server;

import ServiceCommands.ServiceCommands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 5353;
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private List<ClientHandler> client;
    private AuthService authService;
    private ExecutorService exeServ;

    public ExecutorService getExeServ() {
        return exeServ;
    }

    public Server() {

        exeServ = Executors.newCachedThreadPool();

        client = new CopyOnWriteArrayList<>();

        if (!SQLHandler.connect()) {
            throw new RuntimeException("Cannot connect to the DataBase");
        }
        authService = new DatBasAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started successfully");


            while (true) {


                socket = server.accept();
                System.out.println("Client connected");
                System.out.println("client: " + socket.getRemoteSocketAddress());
                new ClientHandler(this, socket);


            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SQLHandler.disconnect();
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
        SQLHandler.addMsg(sender.getNickname(),"null", msg, "Not a long time ago");
        for (ClientHandler clientHandler : client) {
            clientHandler.sendMsg(message);
        }
    }

    public void personalMsg (ClientHandler sender, String recipient, String msg) {
        String message = String.format("[ %s ] writes to [ %s ]: %s", sender.getNickname(), recipient, msg);
        for (ClientHandler clientHandler : client) {
            if (clientHandler.getNickname().equals(recipient)) {
                clientHandler.sendMsg(message);

                SQLHandler.addMsg(sender.getNickname(), recipient, msg, "Not a long time ago");

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
        broadcastClist();
    }

    public void unsubscribe (ClientHandler clientHandler) {
        client.remove(clientHandler);
        broadcastClist();
    }

    public AuthService getAuthService () {
        return authService;
    }

    public boolean isLoginAuth (String login) {
        for (ClientHandler clientHandler : client) {
            if (clientHandler.getLogin().equals(login)) {
                return true;
            }
            
        }
        return false;

    }

    public void broadcastClist () {

        StringBuilder stringBuilder  = new StringBuilder(ServiceCommands.CLIENT_LIST);
        for (ClientHandler clientHandler : client) {
            stringBuilder.append (" ").append (clientHandler.getNickname()) ;
        }
        String message  = stringBuilder.toString();
        for (ClientHandler clientHandler : client) {
            clientHandler.sendMsg(message);
        }
    }

}
