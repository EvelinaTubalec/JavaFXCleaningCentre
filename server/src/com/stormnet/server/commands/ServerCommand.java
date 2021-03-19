package com.stormnet.server.commands;

import org.json.JSONObject;
import org.json.JSONWriter;

import javax.xml.transform.TransformerException;

public interface ServerCommand {

    void processRequest(JSONObject object, JSONWriter jsonWriter) throws TransformerException;
}
