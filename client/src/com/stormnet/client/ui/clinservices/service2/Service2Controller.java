package com.stormnet.client.ui.clinservices.service2;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class Service2Controller {
    public void toOrderButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service2Window");
        windowsFactory.showWindow("addService2Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service2Window");
        windowsFactory.showWindow("priceListWindow");
    }
}
