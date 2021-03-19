package com.stormnet.client.ui.review.reviews;

import com.stormnet.client.ui.common.BaseWindow;
import javafx.scene.Scene;
import java.io.IOException;

public class ShowAllReviewsWindow extends BaseWindow {
    public ShowAllReviewsWindow() throws IOException {
        super("all-reviews.fxml");

        setTitle("Отзывы клиентов");
        setScene(new Scene(getRootUi(), 375, 350));
    }
}
