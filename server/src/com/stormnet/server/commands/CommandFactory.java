package com.stormnet.server.commands;

import com.stormnet.server.commands.implementCommands.NotFoundCommand;
import com.stormnet.server.commands.implementCommands.auth.AuthenticationClientCommand;
import com.stormnet.server.commands.implementCommands.auth.AuthenticationManagerCommand;
import com.stormnet.server.commands.implementCommands.order.*;
import com.stormnet.server.commands.implementCommands.registration.ClientRegistrationCommand;
import com.stormnet.server.commands.implementCommands.review.GetAllReviewsCommand;
import com.stormnet.server.commands.implementCommands.review.SaveClientReviewCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<String, ServerCommand> allCommands = new HashMap<>();

    public CommandFactory() {
        allCommands.put("auth-manager-command", new AuthenticationManagerCommand());
        allCommands.put("auth-client-command", new AuthenticationClientCommand());
        allCommands.put("recording-command", new SaveNewOrderCommand());
        allCommands.put("get-all-orders-command", new GetAllOrdersCommand());
        allCommands.put("get-order-by-id-command", new GetOrderByIdCommand());
        allCommands.put("client-registration-command", new ClientRegistrationCommand());
        allCommands.put("save-review-command", new SaveClientReviewCommand());
        allCommands.put("get-all-reviews-command", new GetAllReviewsCommand());
        allCommands.put("save-order-command", new SaveOrderCommand());
        allCommands.put("delete-order-command", new DeleteOrderCommand());
    }

    public ServerCommand getCommandByName(String commandName) {
        ServerCommand command = allCommands.get(commandName);
        if (command == null) {
            return new NotFoundCommand();
        }
        return command;
    }
}
