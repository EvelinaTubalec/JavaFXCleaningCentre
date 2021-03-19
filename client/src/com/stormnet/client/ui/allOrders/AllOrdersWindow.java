package com.stormnet.client.ui.allOrders;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import javafx.stage.Modality;
import java.io.IOException;

public class AllOrdersWindow extends BaseWindow {

    public AllOrdersWindow() throws IOException {
        super("all-orders-view.fxml");

        setTitle("Список всех заказов");
        initModality(Modality.APPLICATION_MODAL);
        setScene(new Scene(getRootUi(), 1150, 600));
    }
}
