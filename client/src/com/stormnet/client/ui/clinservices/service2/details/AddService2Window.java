package com.stormnet.client.ui.clinservices.service2.details;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

import java.io.IOException;

public class AddService2Window extends BaseWindow {

    public AddService2Window() throws IOException {
        super("add-servies.fxml");

        setTitle("Оформить заказ");
        setScene(new Scene(getRootUi(), 500, 400));
    }
}
