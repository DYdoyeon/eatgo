package kr.co.fastcampus.eatgos.application;


import kr.co.fastcampus.eatgos.domain.Review;
import kr.co.fastcampus.eatgos.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //public Review addReview(Long restaurantId, Review review){
     //   review.setRestaurantId(restaurantId);
      //  return reviewRepository.save(review);

    //}

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

}
