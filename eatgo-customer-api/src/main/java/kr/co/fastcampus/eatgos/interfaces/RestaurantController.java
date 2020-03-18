package kr.co.fastcampus.eatgos.interfaces;

import kr.co.fastcampus.eatgos.application.RestaurantService;
import kr.co.fastcampus.eatgos.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/restaurants")
    public List<Restaurant> list(@RequestParam("region") String region){

       // List<Restaurant> restaurants = new ArrayList<>();

     //   restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));

        List<Restaurant> restaurants =restaurantService.getRestaurants(region);

        return restaurants;
    }



   @GetMapping("/restaurants/{id}")
   public Restaurant detail(@PathVariable("id") final Long id)
   {
      
          Restaurant restaurant = restaurantService.getRestaurant(id);
   
      return restaurant;
   }


}
