package com.ntg.clothes_online_project.entity;

import com.ntg.clothes_online_project.enums.Gender;
import com.ntg.clothes_online_project.enums.Size;

import javax.persistence.*;
import java.awt.*;
import java.net.URL;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private double productPrice;

    @Column(name = "image")
    private URL productImage;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size productSize;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender productGender;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "already_bought")
    private boolean isAddedToCart;

    @Column(name = "bought_items_count")
    private int boughtItemsCount;

    @Column(name = "color")
    private Color productColor;

    public Product() {
    }

    public Product(String productName, double productPrice, URL productImage, Size productSize, Gender productGender, String productDescription, boolean isAddedToCart, int boughtItemsCount, Color productColor) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productSize = productSize;
        this.productGender = productGender;
        this.productDescription = productDescription;
        this.isAddedToCart = isAddedToCart;
        this.boughtItemsCount = boughtItemsCount;
        this.productColor = productColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public URL getProductImage() {
        return productImage;
    }

    public void setProductImage(URL productImage) {
        this.productImage = productImage;
    }

    public Size getProductSize() {
        return productSize;
    }

    public void setProductSize(Size productSize) {
        this.productSize = productSize;
    }

    public Gender getProductGender() {
        return productGender;
    }

    public void setProductGender(Gender productGender) {
        this.productGender = productGender;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public boolean isAddedToCart() {
        return isAddedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        isAddedToCart = addedToCart;
    }

    public int getBoughtItemsCount() {
        return boughtItemsCount;
    }

    public void setBoughtItemsCount(int boughtItemsCount) {
        this.boughtItemsCount = boughtItemsCount;
    }

    public Color getProductColor() {
        return productColor;
    }

    public void setProductColor(Color productColor) {
        this.productColor = productColor;
    }
}
