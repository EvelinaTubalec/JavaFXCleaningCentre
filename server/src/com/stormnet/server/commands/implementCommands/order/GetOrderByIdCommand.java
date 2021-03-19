package com.stormnet.server.commands.implementCommands.order;

import com.stormnet.data.Order;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import com.stormnet.server.dao.OrderDao;
import com.stormnet.utils.date.DataUtils;
import org.json.JSONObject;
import org.json.JSONWriter;

public class GetOrderByIdCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        Long orderId = object.getLong("order-id");

        OrderDao orderDao = DaoFactory.getCurrentDaoFactory().getOrderDao();
        Order order = orderDao.getOrderById(orderId);

        jsonWriter.object();
        jsonWriter.key("response-code").value(200);
        jsonWriter.key("response-message").value("OK");

        jsonWriter.key("response-data").object();
        jsonWriter.key("id").value(order.getId());
        jsonWriter.key("lastName").value(order.getLastName());
        jsonWriter.key("firstName").value(order.getFirstName());
        String dateOfOrderStr = DataUtils.buildStringFromDate(order.getDateOfOrder());
        jsonWriter.key("dateOfOrder").value(dateOfOrderStr);
        jsonWriter.key("address").value(order.getAddress());
        jsonWriter.key("email").value(order.getEmail());
        jsonWriter.key("telephoneNumber").value(order.getTelephoneNumber());
        jsonWriter.endObject();
        jsonWriter.endObject();
    }
}
