package com.restaurant.productservice.service;



import com.restaurant.productservice.Dto.RequestMenuDTO;
import com.restaurant.productservice.Dto.ResponseMenuDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {
     ResponseMenuDTO addMenu(RequestMenuDTO requestMenuDTO);
     List<ResponseMenuDTO> getAllMenus();
     ResponseMenuDTO getMenuById(Long id);
     void deleteMenu(Long id);
     ResponseMenuDTO updateMenu(Long id, RequestMenuDTO requestMenuDTO);
    List<ResponseMenuDTO> searchMenus(String name);

    List<ResponseMenuDTO> getAvailableMenus();
    public Page<ResponseMenuDTO> getMenusPaginated(int page, int size);


}
