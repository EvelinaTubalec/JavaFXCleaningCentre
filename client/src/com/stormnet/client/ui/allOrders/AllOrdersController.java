package com.stormnet.client.ui.allOrders;

import com.stormnet.client.service.ClientService;
import com.stormnet.client.service.imp.ClientServiceImpl;
import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.client.ui.recording.RecordNewOrderController;
import com.stormnet.data.Order;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllOrdersController implements Initializable {

    private ObservableList<Order> allOrdersObservable;

    @FXML
    private TableView<Order> allOrderTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Order, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(200);
        actionsColumn.setCellValueFactory(new ActionsCellFactory());

        allOrderTable.getColumns().add(actionsColumn);
        allOrdersObservable = FXCollections.observableArrayList();
        allOrderTable.setItems(allOrdersObservable);

        try {
            reloadListDataFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPersonBtnPressed(ActionEvent actionEvent) throws IOException {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        RecordNewOrderController controller = (RecordNewOrderController)windowsFactory.getWindowController("recordingWindow");
        controller.clearForm();

        windowsFactory.showWindow("recordingWindow");
    }

    public void reloadListDataFromServer() throws IOException {
        ClientService orderService = new ClientServiceImpl();
        List<Order> allOrders = orderService.getAllOrders();

        allOrdersObservable.clear();
        allOrdersObservable.addAll(allOrders);
    }

    public void exitBtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("allOrdersWindow");
        windowsFactory.showWindow("managerCabinetWindow");
    }

    private class ActionsCellFactory implements Callback<TableColumn.CellDataFeatures<Order, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Order, GridPane> param) {
            Order order = param.getValue();

            Button editBtn = new Button("Edit");
            editBtn.setOnAction(new EditUserEvent(order.getId()));

            Button deleteBtn = new Button("Delete");
            deleteBtn.setOnAction(new DeleteUserEvent(order.getId()));

            GridPane panel = new GridPane();
            panel.setHgap(10);
            panel.setVgap(10);
            panel.setPadding(new Insets(10, 10, 10, 10));

            panel.add(editBtn, 0, 0);
            panel.add(deleteBtn, 1, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }

    private class EditUserEvent implements EventHandler<ActionEvent> {

        private Long orderId;

        public EditUserEvent(Long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void handle(ActionEvent event) {
            ClientServiceImpl clientService = new ClientServiceImpl();
            Order dbOrder = clientService.getOrderById(orderId);
            Object controller = WindowsFactory.getWindowFactory().getWindowController("recordingWindow");
            RecordNewOrderController recordNewOrderController = (RecordNewOrderController) controller;
            recordNewOrderController.fillOrderForm(dbOrder);

            WindowsFactory.getWindowFactory().showWindow("recordingWindow");
        }
    }

    private class DeleteUserEvent implements EventHandler<ActionEvent> {

        private Long orderId;

        public DeleteUserEvent(Long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void handle(ActionEvent event) {
            ClientServiceImpl orderService = new ClientServiceImpl();
            try {
                orderService.deleteOrder(orderId);
                reloadListDataFromServer();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}
