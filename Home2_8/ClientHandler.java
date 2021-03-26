package server;

import ServiceCommands.ServiceCommands;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;
    private String login;

    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {

                try {

                    socket.setSoTimeout(120000);

                    while (true) {
                        String str = in.readUTF();
                        if (str.equals(ServiceCommands.END)) {
                            out.writeUTF(ServiceCommands.END);
                            throw new RuntimeException("Client decided to disconnect ");
                        }
                        if (str.startsWith(ServiceCommands.AUTH)) {
                            String[] token = str.split("\\s");
                            if (token.length < 3) {
                                continue;
                            }
                            String newNick = server.getAuthService().getNickByLoginPassword(token[1], token[2]);
                            login = token[1];
                            if (newNick != null) {
                                if (!server.isLoginAuth(login)) {
                                    nickname = newNick;
                                    sendMsg(ServiceCommands.AUTH_OK + " " + nickname);
                                    server.subscribe(this);
                                    System.out.println("CLient: " + socket.getRemoteSocketAddress() + " connected with nickname: " + nickname);
                                    socket.setSoTimeout(0);
                                    break;
                                } else {
                                    sendMsg("This account is already exist ");
                                }


                            } else {
                                sendMsg("Invalid login or password ");
                            }

                        }
                        if (str.startsWith(ServiceCommands.REG)) {
                            String[] token = str.split("\\s, ", 4);
                            if (token.length < 4) {
                                continue;
                            }

                            boolean regSuccess = server.getAuthService()
                                    .registration(token[1], token[2], token[3]);
                            if (regSuccess) {
                                sendMsg(ServiceCommands.REG_OK);
                            } else {
                                sendMsg(ServiceCommands.REG_NO);
                            }


                        }

                    }

                    while (true) {
                        String str = in.readUTF();

                        if (str.startsWith("/")) {
                            if (str.equals(ServiceCommands.END)) {
                                out.writeUTF(ServiceCommands.END);
                                break;
                            }

                            if (str.startsWith(ServiceCommands.PERSONAL_MSG)) {
                                String[] token = str.split("\\s", 3);
                                if (token.length < 3) {
                                    continue;
                                }
                                server.personalMsg(this, token[1], token[2]);

                            }

                        } else {

                            server.broadcastMsg(this, str);

                        }
                    }
                } catch (SocketTimeoutException e) {
                    sendMsg(ServiceCommands.END);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(this);
                    System.out.println("Client disconnected: " + nickname);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void sendMsg(String msg) {

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }
}
