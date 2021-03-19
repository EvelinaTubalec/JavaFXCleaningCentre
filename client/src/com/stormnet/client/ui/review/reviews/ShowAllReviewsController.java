package com.stormnet.client.ui.review.reviews;

import com.stormnet.client.ui.common.WindowsFactory;
import com.stormnet.data.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowAllReviewsController implements Initializable {

    private ObservableList<Review> allReviewsObservable;

    @FXML
    private TableView<Review> allReviewsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allReviewsObservable = FXCollections.observableArrayList();
        allReviewsTable.setItems(allReviewsObservable);

        List<Review> allReviews;
        try {
            allReviews = getAllReviews();
            reloadListDataFromServer(allReviews);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void reloadListDataFromServer(List<Review> allReviews) {
        allReviewsObservable.clear();
        allReviewsObservable.addAll(allReviews);
    }

    public void exitBtnPressed(ActionEvent actionEvent) {
        WindowsFactory windowsFactory = WindowsFactory.getWindowFactory();
        windowsFactory.hideWindow("showAllReviewsWindow");
        windowsFactory.showWindow("managerCabinetWindow");
    }

    public List<Review> getAllReviews() throws IOException {
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 4488);
        OutputStream serverOs = socket.getOutputStream();
        InputStream serverIs = socket.getInputStream();

        OutputStreamWriter osr = new OutputStreamWriter(serverOs);
        BufferedWriter bufferedWriter = new BufferedWriter(osr);
        JSONWriter jsonWriter = new JSONWriter(bufferedWriter);

        jsonWriter.object();
        jsonWriter.key("request-header").object();
        jsonWriter.key("command-name").value("get-all-reviews-command");
        jsonWriter.endObject();

        jsonWriter.key("request-data").object();
        jsonWriter.endObject();
        jsonWriter.endObject();

        bufferedWriter.flush();

        JSONTokener jsonTokener = new JSONTokener(serverIs);
        JSONObject responseJson = (JSONObject) jsonTokener.nextValue();

        List<Review> allReviews = new ArrayList<>();

        int responseCode = responseJson.getInt("response-code");

        if (responseCode == 200) {
            JSONArray allClientsJson = responseJson.getJSONArray("response-data");
            int clientsCount = allClientsJson.length();

            for (int i = 0; i < clientsCount; i++) {
                JSONObject clientJson = allClientsJson.getJSONObject(i);

                Long id = clientJson.getLong("id");
                String telephoneNumber = clientJson.getString("telephoneNumber");
                String reviewText = clientJson.getString("reviewText");

                Review review = new Review(telephoneNumber, reviewText);

                review.setId(id);

                allReviews.add(review);
            }
        }

        serverOs.close();
        serverIs.close();

        return allReviews;
    }
}

