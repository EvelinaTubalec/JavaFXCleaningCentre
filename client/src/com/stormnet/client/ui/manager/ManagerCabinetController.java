package com.stormnet.client.ui.manager;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class ManagerCabinetController {
    public void allCurrentOrders(ActionEvent actionEvent) {
        WindowsFactory factory = WindowsFactory.getWindowFactory();
        factory.hideWindow("managerCabinetWindow");
        factory.showWindow("allOrdersWindow");
    }

    public void clientReviews(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("managerCabinetWindow");
        windowsFactory.showWindow("showAllReviewsWindow");
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("managerCabinetWindow");
        windowsFactory.showMainWindow();
    }
}
