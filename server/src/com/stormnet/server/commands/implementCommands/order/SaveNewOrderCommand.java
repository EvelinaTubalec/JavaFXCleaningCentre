package com.stormnet.server.commands.implementCommands.order;

import com.stormnet.data.Order;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import com.stormnet.utils.date.DataUtils;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.Date;

public class SaveNewOrderCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {

        String lastName = object.getString("lastName");
        String firstName = object.getString("firstName");
        String dateStr = object.getString("dateOfOrder");
        String address = object.getString("address");
        String email = object.getString("email");
        String telephoneNumber = object.getString("telephoneNumber");

        Date dateOfOrder = DataUtils.buildDateFromString(dateStr);

        Order order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);

        DaoFactory.getCurrentDaoFactory().getOrderDao().saveOrder(order);

        ServerResponse response = new ServerResponse(200, "OK");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.endObject();
    }
}
