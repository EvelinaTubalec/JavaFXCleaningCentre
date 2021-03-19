package com.stormnet.server.commands.implementCommands.auth;

import com.stormnet.data.ManagerAccount;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.List;

public class AuthenticationManagerCommand implements ServerCommand {

    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        String login = object.getString("login");
        String password = object.getString("password");

        ManagerAccount managerAccount = new ManagerAccount(login, password);

        List<ManagerAccount> managerAccounts = DaoFactory.getCurrentDaoFactory().getManagerDao().getAllManagers();

        ServerResponse serverResponse = null;
        for (ManagerAccount managerFromDb:managerAccounts) {
            if(managerAccount.getLogin().equals(managerFromDb.getLogin()) && managerAccount.getPassword().equals(managerFromDb.getPassword())){
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
