package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.MenuItem;
import kr.co.fastcampus.eatgos.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService{

    @Autowired
	private MenuItemRepository menuItemRepository;


	public MenuItemService(MenuItemRepository menuItemRepository)
    {
		this.menuItemRepository = menuItemRepository;
	}

    public void bulkUpdate(Long restaurantId,List<MenuItem> menuItems)  {
        for (MenuItem menuItem : menuItems){
            menuItem.setRestaurantId(restaurantId);
            menuItemRepository.save(menuItem);
        }
    }
}