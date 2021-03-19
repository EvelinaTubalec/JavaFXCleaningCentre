package com.stormnet.server.commands.implementCommands.order;

import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import com.stormnet.server.dao.OrderDao;
import org.json.JSONObject;
import org.json.JSONWriter;
import javax.xml.transform.TransformerException;

public class DeleteOrderCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) throws TransformerException {
        Long orderId = object.getLong("order-id");

        OrderDao orderDao = DaoFactory.getCurrentDaoFactory().getOrderDao();
        orderDao.deleteClient(orderId);

        jsonWriter.object();
        jsonWriter.key("response-code").value(200);
        jsonWriter.key("response-message").value("OK");
        jsonWriter.endObject();
    }
}
