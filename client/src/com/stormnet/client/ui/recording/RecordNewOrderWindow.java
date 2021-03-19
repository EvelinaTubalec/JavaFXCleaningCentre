package com.stormnet.client.ui.recording;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class RecordNewOrderWindow extends BaseWindow {

    public RecordNewOrderWindow() throws IOException {
        super("record-new-order.fxml");

        setTitle("Оформление новой заявки");
        setScene(new Scene(getRootUi(), 500, 400));
    }
}
