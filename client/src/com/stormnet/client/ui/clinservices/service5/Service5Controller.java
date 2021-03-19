package com.stormnet.client.ui.clinservices.service5;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class Service5Controller {

    public void toOrderButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service5Window");
        windowsFactory.showWindow("addService5Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service5Window");
        windowsFactory.showWindow("priceListWindow");
    }
}
