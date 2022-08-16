package com.ntg.clothes_online_project.controller;

import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.service.Impl.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    ProductService productService;

    @PostMapping(value = "/addNewProduct")
    public Product addNewProduct (@RequestBody Product product){
            return productService.createNewProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getById/{id}/")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable(value = "empId") Long id) {
        return productService.deleteProductById(id);
    }
}
