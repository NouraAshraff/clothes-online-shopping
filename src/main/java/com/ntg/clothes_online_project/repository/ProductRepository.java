package com.ntg.clothes_online_project.repository;

import com.ntg.clothes_online_project.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long id);
    List<Product> findByCategory(String category);
    List<Product> findBySize(String size);
    boolean existsByName(String name);
    boolean existsByPrice(Double price);
    boolean existsByImage(String image);
    boolean existsByDescription(String description);
    boolean existsBySize(String size);
    boolean existsByCategory(String category);
    boolean existsByColor(String color);
    boolean existsByBoughtItemsCount(Integer count);



}
