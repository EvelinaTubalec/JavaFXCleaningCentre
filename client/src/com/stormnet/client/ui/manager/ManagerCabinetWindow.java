package com.stormnet.client.ui.manager;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;

public class ManagerCabinetWindow extends BaseWindow {
    public ManagerCabinetWindow() throws Exception {
        super("manager-cabinet.fxml");

        setTitle("Кабинет менеджера");
        setScene(new Scene(getRootUi(), 350, 275));
    }
}
