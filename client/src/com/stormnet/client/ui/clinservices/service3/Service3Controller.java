package com.stormnet.client.ui.clinservices.service3;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class Service3Controller {

    public void toOrderButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service3Window");
        windowsFactory.showWindow("addService3Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service3Window");
        windowsFactory.showWindow("priceListWindow");
    }
}
