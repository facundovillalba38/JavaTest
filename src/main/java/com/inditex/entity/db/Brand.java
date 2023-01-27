package com.inditex.entity.db;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "BRAND_NAME")
    private String brandName;
}
