package com.stormnet.client.ui.clinservices.service1.details;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

import java.io.IOException;

public class AddService1Window extends BaseWindow {

    public AddService1Window() throws IOException {
        super("add-servies.fxml");

        setTitle("Оформить заказ");
        setScene(new Scene(getRootUi(), 500, 400));
    }
}
