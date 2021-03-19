package server;

public class DatBasAuthService implements AuthService{
    @Override
    public String getNickByLoginPassword(String login, String password) {
        return SQLHandler.getNickByLoginPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return SQLHandler.registration(login, password, nickname);
    }

    @Override
    public boolean changeNick(String oldNick, String newNick) {
        return SQLHandler.changeNick(oldNick, newNick);
    }
}
