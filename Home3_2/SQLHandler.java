package server;

import java.sql.*;

public class SQLHandler {

    private static Connection connect;
    private static PreparedStatement psGetNick;
    private static PreparedStatement psReg;
    private static PreparedStatement psChangeNick;

    private static PreparedStatement psAddMsg;
    private static PreparedStatement psGetMsgForNick;

    public static boolean connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:chat.db");
            prepareAllStmt();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static void prepareAllStmt() throws SQLException {
        psGetNick = connect.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?;");
        psReg = connect.prepareStatement("INSERT INTO users (login, password, nickname) VALUES (?, ?. ?);");
        psChangeNick = connect.prepareStatement("UPDATE users SET nickname = ? WHERE nickname = ?;");

        psAddMsg = connect.prepareStatement("INSERT INTO messages (sender, recipient, text, date) VALUES (\n" +
                "(SELECT id FROM users WHERE nickname = ?),\n" +
                "(SELECT id FROM users WHERE nickname = ?),\n" +
                "?, ?)");

        psGetMsgForNick = connect.prepareStatement("SELECT (SELECT nickname FROM users WHERE id = sender), \n" +
                " (SELECT nickname FROM users WHERE id = recipient), \n" +
                " text, \n" +
                " date \n" +
                "FROM messages \n" +
                "WHERE sender = (SELECT id FROM users WHERE nickname = ?) \n" +
                "OR recipient = (SELECT id FROM users WHERE nickname = ?)\n" +
                "OR recipient = (SELECT id FROM users WHERE nickname = 'null')");
    }

    public static String getNickByLoginPassword (String login, String password) {
        String nick = null;

        try {
            psGetNick.setString(1,login);
            psGetNick.setString(2, password);
            ResultSet resSet = psGetNick.executeQuery();
            if (resSet.next()) {
                nick = resSet.getString(1);
            }
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nick;

    }

    public static boolean registration (String login, String password, String nickname) {
        try {
            psChangeNick.setString(1, login);
            psChangeNick.setString(2, password);
            psChangeNick.setString(3, nickname);
            psChangeNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeNick (String oldNick, String newNick) {
        try {
            psChangeNick.setString(1, newNick);
            psChangeNick.setString(2, oldNick);
            psChangeNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean addMsg (String sender, String recipient, String text, String date) {
        try {
            psAddMsg.setString(1, sender);
            psAddMsg.setString(2, recipient);
            psAddMsg.setString(3, text);
            psAddMsg.setString(4, date);
            psAddMsg.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public static String getMsgForNick (String nick) {

        StringBuilder stbld = new StringBuilder();

        try {
            psGetMsgForNick.setString(1, nick);
            psGetMsgForNick.setString(2, nick);
            ResultSet resSet = psGetMsgForNick.executeQuery();

            while (resSet.next()) {
                String sender = resSet.getString(1);
                String recipient = resSet.getString(2);
                String text = resSet.getString(3);
                String date = resSet.getString(4);

                if (recipient.equals("null")) {
                    stbld.append(String.format("[%s] : [%s]\n", sender, text));
                } else {
                    stbld.append(String.format("[%s] to [%s]: [%s]\n", sender, recipient, text));
                }
            } resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stbld.toString();

    }

    public static void disconnect () {
        try {
            psReg.close();
            psGetNick.close();
            psChangeNick.close();
            psAddMsg.close();
            psGetMsgForNick.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
