package com.stormnet.client.ui.review;

import com.stormnet.client.ui.authentication.client.LoginClientController;
import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.function.Consumer;

public class GiveReviewController {

    @FXML
    private TextArea reviewField;

    public void giveReview(ActionEvent actionEvent) throws IOException {
        String reviewText = reviewField.getText();

        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip,4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
        jsonWriter.key("request-header").object();
        jsonWriter.key("command-name").value("save-review-command");
        jsonWriter.endObject();

        Object loginClientWindow = WindowsFactory.getWindowFactory().getWindowController("loginClientWindow");
        LoginClientController loginClientController = (LoginClientController) loginClientWindow;
        String telephoneNumber = loginClientController.getTelephoneNumber();

        jsonWriter.key("request-data").object();
        jsonWriter.key("telephoneNumber").value(telephoneNumber);
        jsonWriter.key("review").value(reviewText);
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        Object o = jsonTokener.nextValue();
        JSONObject responseJson = (JSONObject)o;

        Integer responseCode = responseJson.getInt("response-code");

        serverIs.close();
        serverOs.close();

        if (responseCode == 200){
            clearForm();

            WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
            showReviewsSent("Ваш отзыв отправлен!");
            windowsFactory.hideWindow("giveReviewWindow");
            windowsFactory.showWindow("clientAccountWindow");
        }
    }

    public void cancel(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("giveReviewWindow");
        windowsFactory.showWindow("clientAccountWindow");
    }

    private void showReviewsSent(String errorText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Отзыв отправлен");

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

    public void clearForm(){
        reviewField.setText("");
    }
}
