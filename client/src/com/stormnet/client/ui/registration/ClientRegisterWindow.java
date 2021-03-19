package com.stormnet.client.ui.registration;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class ClientRegisterWindow extends BaseWindow {

    public ClientRegisterWindow() throws IOException {
        super("client-registration-view.fxml");

        setTitle("Регистрация");
        setScene(new Scene(getRootUi(), 400, 375));
    }
}

