package kr.co.fastcampus.eatgos.interfaces;

import kr.co.fastcampus.eatgos.application.RestaurantService;
import kr.co.fastcampus.eatgos.domain.MenuItem;
import kr.co.fastcampus.eatgos.domain.Restaurant;
import kr.co.fastcampus.eatgos.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {
         List<Restaurant> restaurants = new ArrayList<>();


        //given(restaurantService.getRestaurants()).willReturn(restaurants);

        restaurants.add(Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build());

        given(restaurantService.getRestaurants("Seoul")).willReturn(restaurants);
        mvc.perform(get("/restaurants?region=Busan"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")

                ))
                .andExpect(content().string(
                        containsString( "\"name\":\"Bob zip")

                ));


    }
    @Test
    public void detailWithExisted() throws Exception{
      //  final Restaurant restaurant1 = new Restaurant(1004L,"Bob Zip","Seoul");
        Restaurant restaurant1 = Restaurant.builder().id(1004L).name("Bob Zip").address("Seoul").build();

        MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));


        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great!")
                .build();

        restaurant1.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);



        mvc.perform(get("/restaurants/1004")).
                andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")

                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob Zip")
                ))
                .andExpect(content().string(containsString("Kimchi")))
                 .andExpect(content().string(containsString("Great!")))
        ;;



    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(100L))
                .willThrow( new RestaurantNotFoundException(100L));
        mvc.perform(get("/restaurants/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}