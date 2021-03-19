package com.stormnet.server.commands.implementCommands.review;

import com.stormnet.data.Review;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import org.json.JSONObject;
import org.json.JSONWriter;

public class SaveClientReviewCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        String telephoneNumber = object.getString("telephoneNumber");
        String review = object.getString("review");

        Review clientReview = new Review(telephoneNumber, review);

        DaoFactory.getCurrentDaoFactory().getReviewDao().saveReview(clientReview);

        ServerResponse response = new ServerResponse(200, "OK");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.endObject();
    }
}
