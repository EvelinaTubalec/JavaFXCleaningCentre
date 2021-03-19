package com.stormnet.client.ui.authentication.client.details;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.event.ActionEvent;

public class AuthClientController {

    public void signIn(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("authClientWindow");
        windowsFactory.showWindow("loginClientWindow");
    }

    public void registrationNewClient(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("authClientWindow");
        windowsFactory.showWindow("registerWindow");
    }

    public void cancel(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("authClientWindow");
        windowsFactory.showMainWindow();
    }
}
