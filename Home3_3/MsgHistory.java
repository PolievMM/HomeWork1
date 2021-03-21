package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MsgHistory {

    private static PrintWriter out;

    public static String getHistoryFileByLogin (String login) {
        return "history/ history_" + login + ".txt";
    }

    public static void start (String login) {

        try {
            out = new PrintWriter(new FileOutputStream(getHistoryFileByLogin(login), true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void stop () {
        if (out != null) {
            out.close();
        }
    }

    public static void writeLine (String msg) {

        out.println(msg);

    }

    public static String get100LastMsg (String login) {
        if (!Files.exists(Paths.get(getHistoryFileByLogin(login)))) {
            return "";
        }
        StringBuilder strBuild = new StringBuilder();
        try {
            List<String> historyLines = Files.readAllLines(Paths.get(getHistoryFileByLogin(login)));
            int startPosition = 0;
            if (historyLines.size() > 100) {
                startPosition = historyLines.size() - 100;
            }
            for (int i = startPosition; i < historyLines.size(); i++) {
                strBuild.append(historyLines.get(i)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuild.toString();
    }

}
