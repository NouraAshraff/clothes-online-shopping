package com.ntg.clothes_online_project.repository;

import com.ntg.clothes_online_project.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Optional<Product> findById(Long id);

}
