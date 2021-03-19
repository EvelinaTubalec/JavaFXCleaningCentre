package com.stormnet.server.commands.implementCommands.order;

import com.stormnet.data.Order;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import com.stormnet.server.dao.OrderDao;
import com.stormnet.utils.date.DataUtils;
import com.stormnet.utils.numbers.NumberUtils;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.Date;

public class SaveOrderCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        Long orderId = NumberUtils.parseLong(object.getString("order-id"));
        String lastName = object.getString("lastName");
        String firstName = object.getString("firstName");
        String dateStr = object.getString("dateOfOrder");

        String address = object.getString("address");
        String email = object.getString("email");
        String telephoneNumber = object.getString("telephoneNumber");

        Date dateOfOrder = DataUtils.buildDateFromString(dateStr);//ВОЗВРАЩАЕТСЯ NULL

        Order order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
        order.setId(orderId);

        DaoFactory currentDaoFactory = DaoFactory.getCurrentDaoFactory();
        OrderDao orderDao = currentDaoFactory.getOrderDao();

        if (orderId == null) {
            orderId = orderDao.saveOrder(order);
        } else {
            orderDao.editOrder(order);
        }

        ServerResponse response = new ServerResponse(200, "OK");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.key("response-data").object();
        jsonWriter.key("order-id").value(orderId);
        jsonWriter.endObject();
        jsonWriter.endObject();
    }
}
