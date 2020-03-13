package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.Review;
import kr.co.fastcampus.eatgos.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;


public class ReviewServiceTest
{
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);

    }
    @Test
    public void getReviews(){
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder().description("Cool!").build());
        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();

        Review review = reviews.get(0);
        assertThat(review.getDescription(),is("Cool!"));

    }
}