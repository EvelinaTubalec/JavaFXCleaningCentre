package com.stormnet.client.ui.clientaccount;

import com.stormnet.client.service.imp.ClientServiceImpl;
import com.stormnet.client.ui.clinservices.service1.details.AddService1Controller;
import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.client.ui.history.HistoryOfOrdersController;
import com.stormnet.data.Order;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientAccountController {

    public void orderService(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("clientAccountWindow");
        windowsFactory.showWindow("priceListWindow");
    }

    public void orderHistory(ActionEvent actionEvent) throws IOException {
        Object historyWindow = WindowsFactory.getWindowFactory().getWindowController("historyWindow");
        HistoryOfOrdersController historyWindowController = (HistoryOfOrdersController) historyWindow;
        historyWindowController.reloadListDataFromServer(getAllOrders());

        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("clientAccountWindow");
        windowsFactory.showWindow("historyWindow");
    }

    public List<Order> getAllOrders() throws IOException {
        ClientServiceImpl clientService = new ClientServiceImpl();
        List<Order> allOrders = clientService.getAllOrders();

        List<Order> historyOfOrders = new ArrayList<>();

        Object addService1WindowController = WindowsFactory.getWindowFactory().getWindowController("addService1Window");
        AddService1Controller addService1Window = (AddService1Controller) addService1WindowController;
        Order orderByTelephoneFromRecordingForm = addService1Window.getCurrentOrderByTelephone();

        for (Order orderFromDataBase: allOrders) {
            if(orderFromDataBase.getTelephoneNumber().equals(orderByTelephoneFromRecordingForm.getTelephoneNumber())){
                String kindOfOrder = orderFromDataBase.getFirstName();
                Date dateOfOrder = orderFromDataBase.getDateOfOrder();
                String address = orderFromDataBase.getAddress();
                Order order = new Order(kindOfOrder,dateOfOrder, address);
                historyOfOrders.add(order);
            }
        }
        return historyOfOrders;
    }

    public void leaveReview(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("clientAccountWindow");
        windowsFactory.showWindow("giveReviewWindow");
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("clientAccountWindow");
        windowsFactory.showWindow("loginClientWindow");
    }
}
