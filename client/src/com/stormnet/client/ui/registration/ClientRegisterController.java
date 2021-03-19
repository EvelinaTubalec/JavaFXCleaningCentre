package com.stormnet.client.ui.registration;

import com.stormnet.client.ui.common.WindowsFactory;
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

public class ClientRegisterController {

    @FXML
    private TextField telephoneNumberField;

    @FXML
    private TextField passwordField;

    public void registerNewUser(ActionEvent actionEvent) throws IOException {
        String telephoneNumber = telephoneNumberField.getText();
        String password = passwordField.getText();

        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
        jsonWriter.key("request-header").object();
        jsonWriter.key("command-name").value("client-registration-command");
        jsonWriter.endObject();

        jsonWriter.key("request-data").object();
        jsonWriter.key("telephoneNumber").value(telephoneNumber);
        jsonWriter.key("password").value(password);
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        Object o = jsonTokener.nextValue();
        JSONObject responseJson = (JSONObject) o;

        Integer responseCode = responseJson.getInt("response-code");

        serverIs.close();
        serverOs.close();


        if (responseCode == 401) {
            showLoginFailed("Номер введен некорректно! Попробуйте еще раз!");
        } else if (responseCode == 200) {
            clearForm();
            WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
            windowsFactory.hideWindow("registerWindow");
            windowsFactory.showWindow("loginClientWindow");
        }
    }

    private void showLoginFailed(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка ввода");

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

    public void cancel(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("registerWindow");
        windowsFactory.showWindow("loginClientWindow");
    }

    public void clearForm(){
        telephoneNumberField.setText("");
        passwordField.setText("");
    }
}
