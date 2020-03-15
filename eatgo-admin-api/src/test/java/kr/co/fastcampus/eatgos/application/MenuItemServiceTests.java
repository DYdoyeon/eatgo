package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.MenuItem;
import kr.co.fastcampus.eatgos.domain.MenuItemRepository;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTests {
    private MenuItemService menuItemService;
    @Mock
    private MenuItemRepository menuItemRepository ;
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
       menuItemService = new MenuItemService(menuItemRepository);

    }
    
    @Test
    public void bulkUpdate(){
        final List<MenuItem> menuItems = new ArrayList<MenuItem>();
  
       menuItems.add(MenuItem.builder().name("kimchi").build());
       menuItems.add(MenuItem.builder().name("Gokbab").build());

       menuItemService.bulkUpdate(1L,menuItems); 
        verify(menuItemRepository,times(2)).save(any());
    }
    @Test
    public void getMenuItems(){

        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(MenuItem.builder().name("Kimchi").build());

        given(menuItemRepository.findAllByRestaurantId(1004L))
                .willReturn(mockMenuItems);

        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);
        MenuItem menuItem = menuItems.get(0);
        assertThat(menuItem.getName(),is("Kimchi"));

    }

}