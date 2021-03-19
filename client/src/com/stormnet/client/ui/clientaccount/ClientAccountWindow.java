package com.stormnet.client.ui.clientaccount;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class ClientAccountWindow extends BaseWindow {
    public ClientAccountWindow() throws Exception {
        super("client's-account.fxml");

        setTitle("Личный кабинет клиента");
        setScene(new Scene(getRootUi(), 400, 375));
    }
}