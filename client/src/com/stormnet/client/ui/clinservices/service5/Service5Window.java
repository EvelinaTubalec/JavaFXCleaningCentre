package com.stormnet.client.ui.clinservices.service5;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class Service5Window extends BaseWindow {
    public Service5Window() throws Exception {
        super("service-5.fxml");

        setTitle("ХИМЧИСТКА МЕБЕЛИ И ПОКРЫТИЙ:");
        setScene(new Scene(getRootUi(), 650, 430));
    }
}

