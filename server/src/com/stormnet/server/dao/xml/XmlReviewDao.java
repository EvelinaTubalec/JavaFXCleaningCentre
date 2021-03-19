package com.stormnet.server.dao.xml;

import com.stormnet.data.Order;
import com.stormnet.data.Review;
import com.stormnet.server.dao.DaoException;
import com.stormnet.server.dao.ReviewDao;
import com.stormnet.server.dao.xml.db.XmlDataBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

public class XmlReviewDao implements ReviewDao {
    @Override
    public List<Review> getAllReviews() {
        List<Review> allReviews = new ArrayList<>();
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("reviews");

            NodeList allReviewsTag = document.getElementsByTagName("review");
            int tagCount = allReviewsTag.getLength();

            for (int i = 0; i <tagCount ; i++) {
                Element clientTag = (Element) allReviewsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                long id = Long.parseLong(idStr);

                Element telephoneNumberTag = (Element) clientTag.getElementsByTagName("telephoneNumber").item(0);
                String telephoneNumber = telephoneNumberTag.getTextContent();

                Element reviewTextTag = (Element) clientTag.getElementsByTagName("reviewText").item(0);
                String reviewText = reviewTextTag.getTextContent();

                Review review = new Review(telephoneNumber, reviewText);
                review.setId(id);

                allReviews.add(review);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return allReviews;
    }

    @Override
    public Long saveReview(Review review) {
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("reviews");

            Element rootTag = document.getDocumentElement();
            Element reviewTag = document.createElement("review");

            Long id = dataBase.getNexIdForTable("reviews");
            Element idTag = document.createElement("id");
            Text idData = document.createTextNode(id.toString());
            idTag.appendChild(idData);
            reviewTag.appendChild(idTag);

            Element telephoneNumberTag = document.createElement("telephoneNumber");
            Text telephoneNumberData = document.createTextNode(review.getTelephoneNumber());
            telephoneNumberTag.appendChild(telephoneNumberData);
            reviewTag.appendChild(telephoneNumberTag);

            Element reviewTextTag = document.createElement("reviewText");
            Text reviewTextData = document.createTextNode(review.getReviewText());
            reviewTextTag.appendChild(reviewTextData);
            reviewTag.appendChild(reviewTextTag);

            rootTag.appendChild(reviewTag);

            dataBase.saveDbTableDocument("reviews");

            return id;

        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
