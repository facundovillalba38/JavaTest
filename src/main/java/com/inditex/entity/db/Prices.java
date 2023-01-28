package com.inditex.entity.db;

import com.inditex.entity.type.CurrencyType;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name="BRAND_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Brand brand;

    @Column(name="PRIORITY")
    private Integer priority;
    @Column(name="START_DATE")
    private LocalDateTime startDate;
    @Column(name="PRICE")
    private Double price;
    @Column(name="CURR")
    @Enumerated(EnumType.STRING)
    private CurrencyType curr;
    @Column(name="END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;

}
