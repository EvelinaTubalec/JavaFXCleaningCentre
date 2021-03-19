package com.stormnet.client.ui.clinservices.service1;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class Service1Controller {

    public void toOrderButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service1Window");
        windowsFactory.showWindow("addService1Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service1Window");
        windowsFactory.showWindow("priceListWindow");
    }
}
