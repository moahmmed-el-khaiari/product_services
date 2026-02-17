package com.restaurant.productservice.repository;

import com.restaurant.productservice.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu , Long> {
    public Menu findMenuByName(String  name);

    List<Menu> findByNameContainingIgnoreCase(String name);

    List<Menu> findByNameContainingIgnoreCaseAndAvailableTrue(String name);

    List<Menu> findByAvailableTrue();
}
