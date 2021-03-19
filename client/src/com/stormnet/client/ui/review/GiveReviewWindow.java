package com.stormnet.client.ui.review;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class GiveReviewWindow extends BaseWindow {
    public GiveReviewWindow() throws IOException {
        super("give-review.fxml");

        setTitle("Оставить отзыв");
        setScene(new Scene(getRootUi(), 375, 350));
    }
}
