package com.stormnet.server.commands.implementCommands;

import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import org.json.JSONObject;
import org.json.JSONWriter;

public class NotFoundCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        ServerResponse response = new ServerResponse(404, "Not Found!");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.endObject();
    }
}
