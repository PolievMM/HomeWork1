package server;

public interface AuthService {

    String getNickByLoginPassword (String login, String password);
    boolean registration (String login, String password, String nickname);

    boolean changeNick (String oldNick, String newNick);
}
