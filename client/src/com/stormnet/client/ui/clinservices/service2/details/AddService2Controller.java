package com.stormnet.client.ui.clinservices.service2.details;

import com.stormnet.client.ui.allOrders.AllOrdersController;
import com.stormnet.client.ui.authentication.client.LoginClientController;
import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.utils.date.DataUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class AddService2Controller {

    @FXML
    private TextField lastNameField;

    private String serviceName = "УБОРКА ПОСЛЕ РЕМОНТА";

    @FXML
    private DatePicker dateOfOrderField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    public void addService(ActionEvent actionEvent) throws IOException {
        String lastName = lastNameField.getText();
        LocalDate dateOfOrder = dateOfOrderField.getValue();
        String address = addressField.getText();
        String email = emailField.getText();

        Object loginClientWindow = WindowsFactory.getWindowFactory().getWindowController("loginClientWindow");
        LoginClientController loginClientController = (LoginClientController) loginClientWindow;
        String telephoneNumber = loginClientController.getTelephoneNumber();

        Date date = DataUtils.localDateToDate(dateOfOrder);
        String dateOfOrderStr = DataUtils.buildStringFromDate(date);

        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter osr = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(osr);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
        jsonWriter.key("request-header").object();
        jsonWriter.key("command-name").value("recording-command");
        jsonWriter.endObject();

        jsonWriter.key("request-data").object();
        jsonWriter.key("lastName").value(lastName);
        jsonWriter.key("firstName").value(serviceName);
        jsonWriter.key("dateOfOrder").value(dateOfOrderStr);
        jsonWriter.key("address").value(address);
        jsonWriter.key("email").value(email);
        jsonWriter.key("telephoneNumber").value(telephoneNumber);
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

        int responseCode = responseJson.getInt("response-code");

        serverOs.close();
        serverIs.close();

        if (responseCode == 200) {
            loadAllClientsFromServer();

            clearForm();

            WindowsFactory factory = WindowsFactory.getWindowFactory();
            factory.hideWindow("addService2Window");
            factory.showWindow("priceListWindow");
        }
    }

    public void cancelBtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("Service2Window");
        windowsFactory.hideWindow("addService2Window");
        windowsFactory.showWindow("priceListWindow");
    }

    private void loadAllClientsFromServer() throws IOException {
        Object allClientsWindow = WindowsFactory.getWindowFactory().getWindowController("allOrdersWindow");
        AllOrdersController clientsController = (AllOrdersController) allClientsWindow;
        clientsController.reloadListDataFromServer();
    }

    public void clearForm(){
        lastNameField.setText("");
        dateOfOrderField.setValue(null);
        addressField.setText("");
        emailField.setText("");
    }
}
