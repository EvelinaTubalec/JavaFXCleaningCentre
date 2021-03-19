package com.stormnet.client.ui.authentication.manager;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class LoginManagerWindow extends BaseWindow {
    public LoginManagerWindow() throws Exception {
        super("login-manager-view.fxml");

        setTitle("Вход в систему");
        setScene(new Scene(getRootUi(), 400, 375));
    }
}
