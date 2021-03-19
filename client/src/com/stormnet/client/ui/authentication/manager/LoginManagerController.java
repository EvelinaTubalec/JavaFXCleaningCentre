package com.stormnet.client.ui.authentication.manager;

import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.data.ManagerAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.function.Consumer;

public class LoginManagerController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    public void signInButtonPressed(ActionEvent actionEvent) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();

        ManagerAccount account = new ManagerAccount(login,password);

        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip,4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
            jsonWriter.key("request-header").object();
            jsonWriter.key("command-name").value("auth-manager-command");
        jsonWriter.endObject();

        jsonWriter.key("request-data").object();
            jsonWriter.key("login").value(account.getLogin());
            jsonWriter.key("password").value(account.getPassword());
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        Object o = jsonTokener.nextValue();
        JSONObject responseJson = (JSONObject)o;

        Integer responseCode = responseJson.getInt("response-code");

        serverIs.close();
        serverOs.close();

        if (responseCode == 401) {
            showLoginFailed("You entered incorrect data! Try Again!");
        } else if (responseCode == 200){
            clearForm();
            WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
            windowsFactory.hideWindow("loginAdminWindow");
            windowsFactory.showWindow("managerCabinetWindow");
        }
    }

    private void showLoginFailed(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error!");

        alert.setContentText(errorText);

        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            }
        });
    }

    public void CancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("loginAdminWindow");
        windowsFactory.showMainWindow();
    }

    public void clearForm(){
        loginField.setText("");
        passwordField.setText("");
    }
}
