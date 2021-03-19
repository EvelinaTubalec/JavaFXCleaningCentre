package com.stormnet.client.ui.clinservices.service1;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class Service1Window extends BaseWindow {
    public Service1Window() throws Exception {
        super("service-1.fxml");

        setTitle("УБОРКА КВАРТИР И КОТТЕДЖЕЙ:");
        setScene(new Scene(getRootUi(), 650, 430));
    }
}

