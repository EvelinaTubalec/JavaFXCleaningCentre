package com.stormnet.server.commands.implementCommands.registration;

import com.stormnet.data.ClientAccount;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import org.json.JSONObject;
import org.json.JSONWriter;

public class ClientRegistrationCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        String telephoneNumber = object.getString("telephoneNumber");
        String password = object.getString("password");

        int length = telephoneNumber.length();
        if (length != 9){
            ServerResponse response = new ServerResponse(401, "Incorrect!");

            jsonWriter.object();
            jsonWriter.key("response-code").value(response.getResponseCode());
            jsonWriter.key("response-message").value(response.getResponseMessage());
            jsonWriter.endObject();
        }else {
            ClientAccount clientAccount = new ClientAccount(telephoneNumber, password);

            DaoFactory.getCurrentDaoFactory().getUserDao().saveUser(clientAccount);

            ServerResponse response = new ServerResponse(200, "OK");

            jsonWriter.object();
            jsonWriter.key("response-code").value(response.getResponseCode());
            jsonWriter.key("response-message").value(response.getResponseMessage());
            jsonWriter.endObject();
        }
    }
}
