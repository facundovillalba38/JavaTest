package com.inditex.entity.db;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "SIZE")
    private String size;
    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;
}
