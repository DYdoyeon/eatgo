package kr.co.fastcampus.eatgos.domain;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
   // Review save(Review review);

    List<Review> findAll();
    List<Review> findAllByRestaurantId(Long restaurantId);
}