package com.stormnet.client.ui.clinservices.service4;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class Service4Controller {

    public void toOrderButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service4Window");
        windowsFactory.showWindow("addService4Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("service4Window");
        windowsFactory.showWindow("priceListWindow");
    }
}
