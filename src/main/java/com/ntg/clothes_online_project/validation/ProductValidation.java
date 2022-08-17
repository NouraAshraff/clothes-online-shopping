package com.ntg.clothes_online_project.validation;

import com.ntg.clothes_online_project.dto.MessageResponse;
import com.ntg.clothes_online_project.entity.Product;
import com.ntg.clothes_online_project.entity.User;
import com.ntg.clothes_online_project.repository.ProductRepository;
import com.ntg.clothes_online_project.repository.UserRepository;
import com.ntg.clothes_online_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<MessageResponse> validateProduct(Product product) {
        if (product.getPrice()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Price can not be null!"));
        }
        if (product.getName()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Name can not be null!"));
        }
        if (product.getImage()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Image Name can not be null!"));
        }
        if (product.getSize()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Size can not be null!"));
        }
        if (product.getCategory()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Category can not be null!"));
        }
        if (product.getDescription()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Description can not be null!"));
        }
        if (product.isAddedToCart()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Please specify if this item was added to cart!"));
        }
        if (product.getBoughtItemsCount() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Bought Items Count can not be null!"));
        }
        if (product.getColor()==null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Color can not be null!"));
        }
        if (isProductExists(product)){
            return ResponseEntity.badRequest().body(new MessageResponse("This product already exists"));
        }
        return null;
    }

    public boolean isProductExists(Product product){
        if(productRepository.existsByName(product.getName()) && productRepository.existsByPrice(product.getPrice()) &&
                productRepository.existsByImage(product.getImage())  && productRepository.existsByDescription(product.getDescription())  &&
                productRepository.existsBySize(product.getSize())  && productRepository.existsByCategory(product.getCategory()) &&
                productRepository.existsByColor(product.getColor())  &&
                productRepository.existsByBoughtItemsCount(product.getBoughtItemsCount()) ){
            return true;
        }
        return false;
    }
}
