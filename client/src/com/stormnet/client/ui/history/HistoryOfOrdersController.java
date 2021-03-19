package com.stormnet.client.ui.history;

import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryOfOrdersController implements Initializable{

    @FXML
    private ObservableList<Order> allOrdersObservable;

    @FXML
    private TableView<Order> allOrdersTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       allOrdersObservable = FXCollections.observableArrayList();
       allOrdersTable.setItems(allOrdersObservable);
    }

    public void reloadListDataFromServer(List<Order> allOrders){
        allOrdersObservable.clear();
        allOrdersObservable.addAll(allOrders);
    }

    public void exitBtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("historyWindow");
        windowsFactory.showWindow("clientAccountWindow");
    }
}
