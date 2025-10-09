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
    private String image_url;
    @Column(name = "item_status")
    private Integer status;
    // Checar se esse é o tipo certo
    @Column(name = "item_type")
    private EnumType type;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
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
