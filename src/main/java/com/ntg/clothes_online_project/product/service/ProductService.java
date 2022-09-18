package com.ntg.clothes_online_project.product.service;

import com.ntg.clothes_online_project.product.enums.Category;
import com.ntg.clothes_online_project.product.enums.Size;
import com.ntg.clothes_online_project.product.repository.ProductRepository;
import com.ntg.clothes_online_project.product.entity.Product;
import com.ntg.clothes_online_project.product.validation.ProductValidation;
import com.ntg.clothes_online_project.user.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductValidation productValidation;

    public ResponseEntity<?> createNewProduct(Product product) {
        ResponseEntity<MessageResponse> validation = productValidation.validateProduct(product);
        if(validation!=null){
            return validation;
        }
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
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

    public List<Product> getBySize(Size size){
        return (List<Product>) productRepository.findBySize(size.name());
    }

    public Product updatePrice(Product productToBeUpdated){
        Product product = (Product) productRepository.findById(productToBeUpdated.getId()).get();
        if(productToBeUpdated.getPrice() != null) {
            product.setPrice(productToBeUpdated.getPrice());
        }
        return productRepository.save(product);
    }

}
