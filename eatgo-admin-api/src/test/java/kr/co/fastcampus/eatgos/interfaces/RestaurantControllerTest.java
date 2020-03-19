package kr.co.fastcampus.eatgos.interfaces;

import kr.co.fastcampus.eatgos.application.RestaurantService;
import kr.co.fastcampus.eatgos.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

        given(restaurantService.getRestaurants()).willReturn(restaurants);
        mvc.perform(get("/restaurants"))
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



        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);



        mvc.perform(get("/restaurants/1004")).
                andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")

                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob Zip")
                ))
       ;



    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(100L)).willThrow( new RestaurantNotFoundException(100L));
        mvc.perform(get("/restaurants/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
    @Test
    public void createWithValidData() throws Exception {
       
        given(restaurantService.addRestaurant(any())).will(invocation ->{
           Restaurant restaurant = invocation.getArgument(0);
           return   Restaurant.builder()
                .id(1004L)
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();

        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"Beryong\"," +
                        "\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1004"))
                .andExpect(content().string("{}"));
        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void createWithInValidData() throws Exception {
     
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" { \"categoryId\" : 1\"name\" : \"\",\"\"address:\"\"}"))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void updateWithValidData() throws Exception { mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"categoryId\":1,\"name\":\"JOKER Bar\"," +
                    "\"address\":\"Busan\"}"))
            .andExpect(status().isOk());

        verify(restaurantService)
                .updateRestaurant(1004L, "JOKER Bar", "Busan");
    }

    @Test
    public void updateWithInValidData() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\":1,\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
        }
       // public void updateWithoutDate() throw Exception{} 처럼 만들어서 테스트하면 좋아
}