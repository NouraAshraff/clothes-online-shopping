package com.ntg.clothes_online_project.controller;

import com.ntg.clothes_online_project.dto.MessageResponse;
import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.enums.Category;
import com.ntg.clothes_online_project.enums.Size;
import com.ntg.clothes_online_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/addNewProduct")
    public ResponseEntity<?> addNewProduct (@RequestBody Product product){
            return productService.createNewProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable(value = "id") Long id) {
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

    @GetMapping(value = "/getProductBySize/{size}")
    public ResponseEntity<?> getProductBySize(@PathVariable(value = "size") Size size){
        List<Product> list =  productService.getBySize(size);
        if (list.isEmpty()) {
            return ResponseEntity.ok().body(new MessageResponse("No " + size.name() + " Products were found"));
        }
        else
            return ResponseEntity.ok().body(list);
    }

    @PutMapping(value  = "/updatePriceById/{id}")
    public ResponseEntity<?> updatePriceById(@RequestBody Product productToBeUpdated){
        productService.updatePrice(productToBeUpdated);
        return ResponseEntity.ok().body(new MessageResponse("Product updated!"));
    }
}
