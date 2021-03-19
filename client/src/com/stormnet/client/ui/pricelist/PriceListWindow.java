package com.stormnet.client.ui.pricelist;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class PriceListWindow extends BaseWindow {
    public PriceListWindow() throws Exception {
        super("price-list.fxml");

        setTitle("Перечень оказываемых услуг:");
        setScene(new Scene(getRootUi(), 470, 375));
    }
}

