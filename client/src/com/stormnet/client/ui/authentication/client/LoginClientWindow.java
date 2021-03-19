package com.stormnet.client.ui.authentication.client;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class LoginClientWindow extends BaseWindow {
    public LoginClientWindow() throws Exception {
        super("login-client-view.fxml");

        setTitle("Вход в личный кабинет клиента");
        setScene(new Scene(getRootUi(), 400, 375));
    }
}
