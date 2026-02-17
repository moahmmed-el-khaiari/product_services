package com.restaurant.productservice.mapper;


import com.restaurant.productservice.Dto.RequestMenuDTO;
import com.restaurant.productservice.Dto.ResponseMenuDTO;
import com.restaurant.productservice.entity.Menu;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public Menu Dto_to_menu(RequestMenuDTO requestMenuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(requestMenuDTO, menu);
        return menu;

    }
    public ResponseMenuDTO menu_to_Dto(Menu menu) {
        ResponseMenuDTO responseMenuDTO = new ResponseMenuDTO();
        BeanUtils.copyProperties(menu,responseMenuDTO);
        return responseMenuDTO;

    }
}
