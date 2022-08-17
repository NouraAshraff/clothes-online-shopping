package com.ntg.clothes_online_project.entity;


import javax.persistence.*;
import java.net.URL;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "image" , length = 500)
    private URL image;

    @Column(name = "size")
    private String size;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "already_bought")
    private Boolean isAddedToCart;

    @Column(name = "bought_items_count")
    private int boughtItemsCount;

    @Column(name = "color")
    private String color;

    public Product() {
    }

    public Product(String name, double price, URL image, String size, String category, String description, boolean isAddedToCart, int boughtItemsCount, String color) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.size = size;
        this.category = category;
        this.description = description;
        this.isAddedToCart = isAddedToCart;
        this.boughtItemsCount = boughtItemsCount;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL productImage) {
        this.image = productImage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
