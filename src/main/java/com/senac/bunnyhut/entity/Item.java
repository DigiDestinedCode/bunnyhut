package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

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
}
