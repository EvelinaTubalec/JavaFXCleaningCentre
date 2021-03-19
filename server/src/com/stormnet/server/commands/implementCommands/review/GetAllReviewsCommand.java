package com.stormnet.server.commands.implementCommands.review;

import com.stormnet.data.Review;
import com.stormnet.data.ServerResponse;
import com.stormnet.server.commands.ServerCommand;
import com.stormnet.server.dao.DaoFactory;
import org.json.JSONObject;
import org.json.JSONWriter;
import java.util.List;

public class GetAllReviewsCommand implements ServerCommand {
    @Override
    public void processRequest(JSONObject object, JSONWriter jsonWriter) {
        List<Review> allReviews = DaoFactory.getCurrentDaoFactory().getReviewDao().getAllReviews();

        ServerResponse response = new ServerResponse(200, "OK");

        jsonWriter.object();
        jsonWriter.key("response-code").value(response.getResponseCode());
        jsonWriter.key("response-message").value(response.getResponseMessage());
        jsonWriter.key("response-data").array();

        for (Review review : allReviews) {
            jsonWriter.object();
            jsonWriter.key("id").value(review.getId());
            jsonWriter.key("telephoneNumber").value(review.getTelephoneNumber());
            jsonWriter.key("reviewText").value(review.getReviewText());
            jsonWriter.endObject();
        }

        jsonWriter.endArray();
        jsonWriter.endObject();
    }
}
