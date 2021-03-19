package com.stormnet.client;

import com.stormnet.client.ui.common.WindowsFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/primarystage/primary-stage.fxml"));

        primaryStage.setTitle("Вход в систему");
        primaryStage.setScene(new Scene(root, 250, 175));

        WindowsFactory factory = WindowsFactory.getWindowFactory();
        factory.setMainWindow(primaryStage);
        factory.showMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
