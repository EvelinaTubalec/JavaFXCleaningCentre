package com.stormnet.client.ui.pricelist;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class PriceListController {

    public void service1BtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("service1Window");
    }

    public void service2ButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("service2Window");
    }

    public void service3ButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("service3Window");
    }

    public void service4ButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("service4Window");
    }

    public void service5ButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("service5Window");
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("priceListWindow");
        windowsFactory.showWindow("clientAccountWindow");
    }
}

