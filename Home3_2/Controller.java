package client;

import ServiceCommands.ServiceCommands;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textfield;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passField;
    @FXML
    public HBox authPanel;
    @FXML
    public HBox msgPanel;
    @FXML
    public ListView<String> clientList;


    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final int PORT = 5353;
    private final String IP_ADDRESS = "192.168.1.67";

    private boolean isAuth;
    private String nickname;
    private Stage stage;
    private Stage regStage;
    private RegController regController;

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
        msgPanel.setVisible(isAuth);
        msgPanel.setManaged(isAuth);
        authPanel.setVisible(!isAuth);
        authPanel.setManaged(!isAuth);
        clientList.setVisible(isAuth);
        clientList.setManaged(isAuth);
        if (!isAuth) {
            nickname = " ";
        }

        textArea.clear();
        setTitle(nickname);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) textArea.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                System.out.println("Bye");
                if (socket != null && !socket.isClosed()) {
                    try {
                        out.writeUTF(ServiceCommands.END);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        });
        setIsAuth(false);


    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {

                try {

//                    цикл аутентификации
                    while (true) {
                        String str = in.readUTF();

                        if (str.startsWith("/")) {
                            if (str.equals(ServiceCommands.END)) {
                                throw new RuntimeException("Server disconnecting client ");
                            }
                            if (str.startsWith(ServiceCommands.AUTH_OK)) {
                                String[] token = str.split("\\s");
                                nickname = token[1];
                                setIsAuth(true);
                                break;

                            }

                            if (str.equals(ServiceCommands.REG_OK)) {
                                regController.resultTryToReg(ServiceCommands.REG_OK);

                            }

                            if (str.equals(ServiceCommands.REG_NO)) {
                                regController.resultTryToReg(ServiceCommands.REG_NO);
                            }

                        } else {
                            textArea.appendText(str + "\n");
                        }

                    }

//                    цикл работы

                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals(ServiceCommands.END)) {
                                System.out.println("Client disconnected");
                                break;
                            }
                            if (str.startsWith(ServiceCommands.CLIENT_LIST)) {

                                String[] token = str.split("\\s");
                                Platform.runLater(() -> {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < token.length; i++) {
                                        clientList.getItems().add(token[i]);
                                    }
                                });

                            }
                            if (str.startsWith("yournickis ")) {
                                nickname = str.split(" ")[1];
                                setTitle(nickname);
                            }

                        } else {
                            textArea.appendText(str + "\n");
                        }

                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    setIsAuth(false);
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

    public void sendMsg(ActionEvent actionEvent) {

        try {
            out.writeUTF(textfield.getText());
            textfield.clear();
            textfield.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void tryToLog(ActionEvent actionEvent) {

        if (socket == null || socket.isClosed()) {

            connect();

        }

        try {
            out.writeUTF(String.format("%s %s %s", ServiceCommands.AUTH, loginField.getText().trim(), passField.getText().trim()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            passField.clear();
        }

    }

    private void setTitle(String nickname) {

        Platform.runLater(() -> {
            if (nickname.equals("")) {
                stage.setTitle("ironmanIndustries");
            } else {
                stage.setTitle(String.format("ironmanIndustries - [ %s]]", nickname));
            }
        });

    }

    public void clistMouseRealesed(MouseEvent mouseEvent) {
        System.out.println(clientList.getSelectionModel().getSelectedItem());
        String msg = String.format("%s %s ", ServiceCommands.PERSONAL_MSG, clientList.getSelectionModel().getSelectedItem());
        textfield.setText(msg);
        textfield.requestFocus();
    }

    public void showRegWindow(ActionEvent actionEvent) {
        if (regStage == null) {
            initRegWindow();
        }

        regStage.show();


    }

    private void initRegWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/reg.fxml"));
            Parent root = fxmlLoader.load();
            regController = fxmlLoader.getController();
            regController.setController(this);

            regStage = new Stage();
            regStage.setTitle("ironmanIndustries/ registration ");
            regStage.setScene(new Scene(root, 450, 350));
            regStage.initStyle(StageStyle.UTILITY);
            regStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registration (String login, String password, String nickname) {
        if (socket == null || socket.isClosed()) {

            connect();
        }
        try {
            out.writeUTF(String.format("%s %s%s %s %s", ServiceCommands.REG, login, password, nickname));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
