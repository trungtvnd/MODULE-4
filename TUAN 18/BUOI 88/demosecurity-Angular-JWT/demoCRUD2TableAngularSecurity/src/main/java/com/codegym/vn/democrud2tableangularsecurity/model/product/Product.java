package com.codegym.vn.democrud2tableangularsecurity.model.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String description;

    private String image;
    @ManyToOne
    private Category category;




}
