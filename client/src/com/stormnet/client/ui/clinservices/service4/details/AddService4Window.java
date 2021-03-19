package com.stormnet.client.ui.clinservices.service4.details;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class AddService4Window extends BaseWindow {

    public AddService4Window() throws IOException {
        super("add-servies.fxml");

        setTitle("Оформить заказ");
        setScene(new Scene(getRootUi(), 500, 400));
    }
}
