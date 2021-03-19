package com.stormnet.client.service.imp;

import com.stormnet.client.service.ClientService;
import com.stormnet.client.service.ServiceException;
import com.stormnet.data.Order;
import com.stormnet.utils.date.DataUtils;
import com.stormnet.utils.numbers.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Override
    public List<Order> getAllOrders() throws IOException {
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter osr = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(osr);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
        jsonWriter.key("request-header").object();
        jsonWriter.key("command-name").value("get-all-orders-command");
        jsonWriter.endObject();

        jsonWriter.key("request-data").object();
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

        List<Order> allOrders = new ArrayList<>();

        int responseCode = responseJson.getInt("response-code");

        if (responseCode == 200) {
            JSONArray allClientsJson = responseJson.getJSONArray("response-data");
            int clientsCount = allClientsJson.length();

            for (int i = 0; i < clientsCount; i++) {
                JSONObject clientJson = allClientsJson.getJSONObject(i);

                Long id = clientJson.getLong("id");
                String lastName = clientJson.getString("lastName");
                String firstName = clientJson.getString("firstName");

                String dateOfOrderStr = clientJson.getString("dateOfOrder");
                Date dateOfOrder = DataUtils.buildDateFromString(dateOfOrderStr);

                String address = clientJson.getString("address");
                String email = clientJson.getString("email");
                String telephoneNumber = clientJson.getString("telephoneNumber");

                Order order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
                order.setId(id);

                allOrders.add(order);
            }
        }

        serverOs.close();
        serverIs.close();

        return allOrders;
    }

    @Override
    public Order getOrderById(Long orderId) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 4488);
            OutputStream serverOs = socket.getOutputStream();
            InputStream serverIs = socket.getInputStream();

            OutputStreamWriter osr = new OutputStreamWriter(serverOs);
            BufferedWriter bufferedWriter = new BufferedWriter(osr);
            JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

            jsonWriter.object();
            jsonWriter.key("request-header").object();
            jsonWriter.key("command-name").value("get-order-by-id-command");
            jsonWriter.endObject();

            jsonWriter.key("request-data").object();
            jsonWriter.key("order-id").value(orderId);
            jsonWriter.endObject();
            jsonWriter.endObject();

            bufferedWriter.flush();

            JSONTokener jsonTokener = new JSONTokener(serverIs);
            JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

            int responseCode = responseJson.getInt("response-code");

            Order order = null;
            if (responseCode == 200) {
                JSONObject object = responseJson.getJSONObject("response-data");

                Long id = object.getLong("id");
                String lastName = object.getString("lastName");
                String firstName = object.getString("firstName");
                String dateStr = object.getString("dateOfOrder");
                Date dateOfOrder = DataUtils.buildDateFromString(dateStr);

                String address = object.getString("address");
                String email = object.getString("email");
                String telephoneNumber = object.getString("telephoneNumber");

                order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
                order.setId(id);
            }

            serverOs.close();
            serverIs.close();

            return order;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long saveOrder(Order order) {
        long orderId = 0;
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 4488);
            OutputStream serverOs = socket.getOutputStream();
            InputStream serverIs = socket.getInputStream();

            OutputStreamWriter osr = new OutputStreamWriter(serverOs);
            BufferedWriter bufferedWriter = new BufferedWriter(osr);
            JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

            jsonWriter.object();
            jsonWriter.key("request-header").object();
            jsonWriter.key("command-name").value("save-order-command");
            jsonWriter.endObject();

            jsonWriter.key("request-data").object();
            jsonWriter.key("order-id").value(NumberUtils.toString(order.getId()));
            jsonWriter.key("lastName").value(order.getLastName());
            jsonWriter.key("firstName").value(order.getFirstName());
            String dateStr = DataUtils.buildStringFromDate(order.getDateOfOrder());
            jsonWriter.key("dateOfOrder").value(dateStr);
            jsonWriter.key("address").value(order.getAddress());
            jsonWriter.key("email").value(order.getEmail());
            jsonWriter.key("telephoneNumber").value(order.getTelephoneNumber());
            jsonWriter.endObject();
            jsonWriter.endObject();

            bufferedWriter.flush();

            JSONTokener jsonTokener = new JSONTokener(serverIs);
            JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

            int responseCode = responseJson.getInt("response-code");
            if (responseCode == 200) {
                JSONObject responseData = responseJson.getJSONObject("response-data");
                orderId = responseData.getLong("order-id");
                return orderId;
            }

            serverOs.close();
            serverIs.close();

        } catch (Exception e) {
            throw new ServiceException(e);
        }
        throw new RuntimeException("Can't Save User!");
    }

    @Override
    public void deleteOrder(Long orderId) throws IOException {
        try{
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 4488);
            OutputStream serverOs = socket.getOutputStream();
            InputStream serverIs = socket.getInputStream();

            OutputStreamWriter osr = new OutputStreamWriter(serverOs);
            BufferedWriter bufferedWriter = new BufferedWriter(osr);
            JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

            jsonWriter.object();
            jsonWriter.key("request-header").object();
            jsonWriter.key("command-name").value("delete-order-command");
            jsonWriter.endObject();

            jsonWriter.key("request-data").object();
            jsonWriter.key("order-id").value(orderId.toString());
            jsonWriter.endObject();
            jsonWriter.endObject();

            bufferedWriter.flush();

            JSONTokener jsonTokener = new JSONTokener(serverIs);
            JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

            int responseCode = responseJson.getInt("response-code");
            if (responseCode != 200) {
                throw new RemoteException("Can't delete order!");
            }

            serverOs.close();
            serverIs.close();

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
