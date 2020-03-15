package kr.co.fastcampus.eatgos.interfaces;

import kr.co.fastcampus.eatgos.application.MenuItemService;
import kr.co.fastcampus.eatgos.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class MenuItemController{
    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody List<MenuItem> menuItems){
     //   List<MenuItem> menuItems = new ArrayList<>();
        menuItemService.bulkUpdate(restaurantId,menuItems);
        return "";
    }


}