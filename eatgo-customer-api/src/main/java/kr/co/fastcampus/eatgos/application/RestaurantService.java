package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.*;


import kr.co.fastcampus.eatgos.domain.Restaurant;
import kr.co.fastcampus.eatgos.domain.MenuItem;
import kr.co.fastcampus.eatgos.domain.RestaurantRepository;
import kr.co.fastcampus.eatgos.interfaces.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }


    public Restaurant getRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                    .orElseThrow(() -> new RestaurantNotFoundException(id));
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews= reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);
        return  restaurant;
    }

    public List<Restaurant> getRestaurants(String region, Long categoryId)
    {

        List<Restaurant> restaurants=restaurantRepository.findAllByAddressContainingAndCategoryId(
                region,categoryId);

        return restaurants;
        
    }

    public Restaurant addRestaurant(Restaurant restaurant) {

       // restaurant.setId(1234L);
       // return new Restaurant(1234L, restaurant.getName(), restaurant.getAddress());
        return restaurantRepository.save(restaurant);
    }

    @Transactional
	public Restaurant updateRestaurant(long id, String name, String address) {
        //TODO : update Restaurant...
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name,address);

        return restaurant;
	}
}
