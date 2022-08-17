package com.ntg.clothes_online_project.controller;

import com.ntg.clothes_online_project.dto.MessageResponse;
import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.enums.Category;
import com.ntg.clothes_online_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/addNewProduct")
    public Product addNewProduct (@RequestBody Product product){
            return productService.createNewProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long id) {
       return productService.getProductById(id);

    }
    @DeleteMapping(value = "/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable(value = "empId") Long id) {
        return productService.deleteProductById(id);
    }

    @GetMapping(value = "/getProductByCategory/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable(value = "category") Category category){
        List<Product> l =  productService.getByCategory(category);
        if (l.isEmpty()) {
            return ResponseEntity.ok().body(new MessageResponse("No " + category.name() + " Products were found"));
        }
        else
            return ResponseEntity.ok().body(l);
    }
}
