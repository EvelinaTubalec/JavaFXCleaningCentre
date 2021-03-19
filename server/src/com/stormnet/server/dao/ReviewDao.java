package com.stormnet.server.dao;

import com.stormnet.data.Review;
import java.util.List;

public interface ReviewDao{

    List<Review> getAllReviews();

    Long saveReview(Review review);

}
