package com.restaurant.productservice.service;

import com.restaurant.productservice.Dto.RequestMenuDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;
import com.restaurant.productservice.Dto.ResponseMenuDTO;
import com.restaurant.productservice.entity.Menu;

import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.enums.ProductCategory;
import com.restaurant.productservice.mapper.MenuMapper;
import com.restaurant.productservice.repository.MenuRepository;
import com.restaurant.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final ProductRepository productRepository;
    @Override
    public ResponseMenuDTO addMenu(RequestMenuDTO dto) {

        Menu menu = menuMapper.Dto_to_menu(dto);

        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {

            List<Product> products =
                    productRepository.findAllById(dto.getProductIds());

            menu.setProducts(products);
        }

        Menu saved = menuRepository.save(menu);

        return menuMapper.menu_to_Dto(saved);
    }

    @Override
    public List<ResponseMenuDTO> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<ResponseMenuDTO> responseMenuDTOS = new ArrayList<>();
        for (Menu menu : menus) {
            responseMenuDTOS.add(menuMapper.menu_to_Dto(menu));
        }
        return  responseMenuDTOS;
    }

    @Override
    public ResponseMenuDTO getMenuById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        return menuMapper.menu_to_Dto(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public ResponseMenuDTO updateMenu(Long id, RequestMenuDTO requestMenuDTO) {
        Menu nv_menu = menuMapper.Dto_to_menu(requestMenuDTO);
        Menu menu = menuRepository.findById(id).orElseThrow();

        if(nv_menu.getName() !=null) menu.setName(nv_menu.getName());
        if(nv_menu.getPrice() !=null) menu.setPrice(nv_menu.getPrice());
        if(nv_menu.getAvailable() !=null) menu.setAvailable(nv_menu.getAvailable());
        if (nv_menu.getDescription() != null) menu.setDescription(nv_menu.getDescription());
        if (nv_menu.getImageUrl() != null) menu.setImageUrl(nv_menu.getImageUrl());
        Menu savedmenu1 = menuRepository.save(menu);
        return menuMapper.menu_to_Dto(savedmenu1);
    }
    @Override
    public List<ResponseMenuDTO> searchMenus(String name) {

        List<Menu> menus =
                menuRepository.findByNameContainingIgnoreCaseAndAvailableTrue(name);

        List<ResponseMenuDTO> response = new ArrayList<>();

        for (Menu menu : menus) {
            response.add(menuMapper.menu_to_Dto(menu));
        }

        return response;
    }
    @Override
    public List<ResponseMenuDTO> getAvailableMenus() {

        List<Menu> menus = menuRepository.findByAvailableTrue();

        List<ResponseMenuDTO> response = new ArrayList<>();

        for (Menu menu : menus) {
            response.add(menuMapper.menu_to_Dto(menu));
        }

        return response;
    }
    @Override
    public Page<ResponseMenuDTO> getMenusPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Menu> menus = menuRepository.findAll(pageable);

        return menus.map(menuMapper::menu_to_Dto);
    }


}
