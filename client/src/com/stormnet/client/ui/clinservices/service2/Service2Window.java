package com.stormnet.client.ui.clinservices.service2;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class Service2Window extends BaseWindow {
    public Service2Window() throws Exception {
        super("service-2.fxml");

        setTitle("УБОРКА ПОСЛЕ РЕМОНТА:");
        setScene(new Scene(getRootUi(), 650, 430));
    }
}
