package com.stormnet.client.ui.authentication.client.details;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class AuthClientWindow extends BaseWindow {

    public AuthClientWindow() throws Exception {
        super("auth-client-window.fxml");

        setTitle("Вход в личный кабинет клиента");
        setScene(new Scene(getRootUi(), 375, 300));
    }
}