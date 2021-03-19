package com.stormnet.client.ui.clinservices.service4;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class Service4Window extends BaseWindow {
    public Service4Window() throws Exception {
        super("service-4.fxml");

        setTitle("МОЙКА ОКОН И ВИТРИН:");
        setScene(new Scene(getRootUi(), 650, 430));
    }
}

