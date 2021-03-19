package com.stormnet.server;

import com.stormnet.server.commands.CommandFactory;
import com.stormnet.server.commands.ServerCommand;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public static void main(String[] args) throws IOException, TransformerException {
        ServerSocket serverSocket = new ServerSocket(4488);
        CommandFactory commandFactory = new CommandFactory();
        while(true){
            Socket clientSocket = serverSocket.accept();

            OutputStream clientOs = clientSocket.getOutputStream();
            InputStream clientIs = clientSocket.getInputStream();

            JSONTokener jsonTokener = new JSONTokener(clientIs);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientOs);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

            Object o = jsonTokener.nextValue();
            JSONObject jsonObject = (JSONObject)o;

            JSONObject header = jsonObject.getJSONObject("request-header");
            JSONObject data = jsonObject.getJSONObject("request-data");

            String commandName = header.getString("command-name");
            ServerCommand command = commandFactory.getCommandByName(commandName);
            command.processRequest(data,jsonWriter);

            bufferedWriter.flush();

            clientIs.close();
            clientOs.close();
        }
    }
}
