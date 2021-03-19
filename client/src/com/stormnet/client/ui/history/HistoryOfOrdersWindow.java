package com.stormnet.client.ui.history;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class HistoryOfOrdersWindow extends BaseWindow {

    public HistoryOfOrdersWindow() throws IOException {
        super("history-of-order.fxml");

        setTitle("История заказов");
        setScene(new Scene(getRootUi(), 500, 400));
    }
}
