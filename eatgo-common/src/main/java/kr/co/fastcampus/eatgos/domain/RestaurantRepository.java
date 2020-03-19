package kr.co.fastcampus.eatgos.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAllByAddressContainingAndCategoryId
            (String name, Long categoryId);

    Restaurant save(Restaurant restaurant);
}
