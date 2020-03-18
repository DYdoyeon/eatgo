package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.MenuItem;
import kr.co.fastcampus.eatgos.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;


@Service
public class MenuItemService{

	@Autowired
	private MenuItemRepository menuItemRepository;

	public void bulkUpdate(Long restaurantId,List<MenuItem> menuItems)  {
		for (MenuItem menuItem : menuItems){
			menuItem.setRestaurantId(restaurantId);
			menuItemRepository.save(menuItem);
		}
	}

    public List<MenuItem> getMenuItems(Long restaurantId){

     return  menuItemRepository.findAllByRestaurantId(restaurantId);

    }
	public MenuItemService(MenuItemRepository menuItemRepository){

	    this.menuItemRepository = menuItemRepository;
	}

	private void update(Long restaurantId, MenuItem menuItem) {
		if (menuItem.isDestroy()) {
			menuItemRepository.deleteById(menuItem.getId());
			return;
		}

		menuItem.setRestaurantId(restaurantId);
		menuItemRepository.save(menuItem);
	}

}