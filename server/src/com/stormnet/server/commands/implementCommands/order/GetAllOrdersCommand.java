package com.stormnet.server.commands.implementCommands.order;

import com.stormnet.data.Order;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import com.stormnet.utils.date.DataUtils;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.List;

public class GetAllOrdersCommand implements ServerCommand {

    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        List<Order> allOrders = DaoFactory.getCurrentDaoFactory().getOrderDao().getAllOrders();

        ServerResponse response = new ServerResponse(200, "OK");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.key("response-data").array();

        for (Order order : allOrders) {
            jsonWriter.object();
            jsonWriter.key("id").value(order.getId());
            jsonWriter.key("lastName").value(order.getLastName());
            jsonWriter.key("firstName").value(order.getFirstName());
            String dataOfOrderStr = DataUtils.buildStringFromDate(order.getDateOfOrder());
            jsonWriter.key("dateOfOrder").value(dataOfOrderStr);
            jsonWriter.key("address").value(order.getAddress());
            jsonWriter.key("email").value(order.getEmail());
            jsonWriter.key("telephoneNumber").value(order.getTelephoneNumber());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }
}