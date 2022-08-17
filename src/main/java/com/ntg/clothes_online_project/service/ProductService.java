package com.ntg.clothes_online_project.service;

import com.ntg.clothes_online_project.dto.MessageResponse;
import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.enums.Category;
import com.ntg.clothes_online_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createNewProduct(Product product) {
        if (product != null) {
            return productRepository.save(product);
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public ResponseEntity<?> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product == null)
            return ResponseEntity.badRequest().body(new MessageResponse("No product by this Id was found"));
        else
            return ResponseEntity.ok().body(product);
    }

    public boolean deleteProductById(Long id) {
        if (id != null) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getByCategory(Category category){
        return (List<Product>) productRepository.findByCategory(category.name());
    }

}
