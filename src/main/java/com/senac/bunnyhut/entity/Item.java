package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_description")
    private String description;
    @Column(name = "item_price")
    private Integer price;
    @Column(name = "item_image_url")
    private String imageUrl;
    @Column(name = "item_status")
    private Integer status;
    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @OneToMany(mappedBy = "item")
    private Set<Background> backgrounds;

    @OneToMany(mappedBy = "item")
    private Set<Furniture> furnitures;

    @OneToMany(mappedBy = "item")
    private Set<Inventory> inventories;

    @OneToMany(mappedBy = "item")
    private Set<Plant> plants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Set<Background> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(Set<Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public Set<Furniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(Set<Furniture> furnitures) {
        this.furnitures = furnitures;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }
}