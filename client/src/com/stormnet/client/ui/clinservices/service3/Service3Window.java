package com.stormnet.client.ui.clinservices.service3;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class Service3Window extends BaseWindow {
    public Service3Window() throws Exception {
        super("service-3.fxml");

        setTitle("УБОРКА ОФИСОВ И ПРОИЗВОДСТВЕННЫХ ПОМЕЩЕНИЙ:");
        setScene(new Scene(getRootUi(), 650, 430));
    }
}

