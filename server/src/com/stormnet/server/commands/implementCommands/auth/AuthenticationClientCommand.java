package com.stormnet.server.commands.implementCommands.auth;

import com.stormnet.data.ClientAccount;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.List;

public class AuthenticationClientCommand implements ServerCommand {

    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        String telephoneNumber = object.getString("telephoneNumber");
        String password = object.getString("password");

        ClientAccount clientAccount = new ClientAccount(telephoneNumber, password);

        List<ClientAccount> allUsers = DaoFactory.getCurrentDaoFactory().getUserDao().getAllUsers();

        ServerResponse serverResponse = null;
        for (ClientAccount clientFromDb:allUsers) {
            if(clientAccount.getTelephoneNumber().equals(clientFromDb.getTelephoneNumber()) && clientAccount.getPassword().equals(clientFromDb.getPassword())){
                serverResponse = new ServerResponse(200, "OK");
                break;
            }
            serverResponse = new ServerResponse(401, "Incorrect!");
        }

        jsonWriter.object();
        jsonWriter.key("response-code").value(serverResponse.getResponseCode());
        jsonWriter.key("response-message").value(serverResponse.getResponseMessage());
        jsonWriter.endObject();
    }
}
