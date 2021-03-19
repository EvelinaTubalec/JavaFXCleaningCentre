package com.stormnet.client.ui.primarystage;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class PrimaryStageController {
    public void loginAsAdministrator(ActionEvent actionEvent) throws Exception {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideMainWindow();
        windowsFactory.showWindow("loginAdminWindow");
    }

    public void loginAsClient(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideMainWindow();
        windowsFactory.showWindow("authClientWindow");
    }
}
