package com.stormnet.client.ui.recording;

import com.stormnet.client.service.ClientService;
import com.stormnet.client.service.imp.ClientServiceImpl;
import com.stormnet.client.ui.allOrders.AllOrdersController;
import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.data.Order;
import com.stormnet.utils.date.DataUtils;
import com.stormnet.utils.numbers.NumberUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RecordNewOrderController {

    @FXML
    private TextField idField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ChoiceBox firstNameField;

    @FXML
    private DatePicker dateOfOrderField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telephoneNumberField;

    public void addNewClient(ActionEvent actionEvent) throws IOException {
        Long orderId = NumberUtils.parseLong(idField.getText());
        String lastName = lastNameField.getText();
        Object typeOfServiceObj = firstNameField.getValue();
        String firstName = typeOfServiceObj.toString();

        LocalDate localDateOfOrder = dateOfOrderField.getValue();
        Date dateOfOrder = DataUtils.localDateToDate(localDateOfOrder);

        String address = addressField.getText();
        String email = emailField.getText();
        String telephoneNumber = telephoneNumberField.getText();

        Order order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
        order.setId(orderId);

        ClientService clientService = new ClientServiceImpl();
        clientService.saveOrder(order);

        loadAllClientsFromServer();

        WindowsFactory factory = WindowsFactory.getWindowFactory();
        factory.hideWindow("recordingWindow");
        factory.showWindow("allOrdersWindow");
    }

    public void cancelBtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("recordingWindow");
        windowsFactory.showWindow("allOrdersWindow");
    }


    private void loadAllClientsFromServer() throws IOException {
        Object allOrdersWindow = WindowsFactory.getWindowFactory().getWindowController("allOrdersWindow");
        AllOrdersController orderController = (AllOrdersController) allOrdersWindow;
        orderController.reloadListDataFromServer();
    }


    public void fillOrderForm(Order order) {
        Long orderId = order.getId();
        if (orderId == null) {
            idField.setText("");
        } else  {
            idField.setText(order.getId().toString());
        }

        lastNameField.setText(order.getLastName());
        firstNameField.setValue(order.getFirstName());
        Date dateOfOrder = order.getDateOfOrder();
        LocalDate localDate = dateOfOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateOfOrderField.setValue(localDate);
        addressField.setText(order.getAddress());
        emailField.setText(order.getEmail());
        telephoneNumberField.setText(order.getTelephoneNumber());
    }

    public void clearForm(){
        idField.setText("");
        lastNameField.setText("");
        firstNameField.setValue(null);
        dateOfOrderField.setValue(null);
        addressField.setText("");
        emailField.setText("");
        telephoneNumberField.setText("");
    }
}
