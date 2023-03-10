package com.inditex.entity.dto;

import com.inditex.entity.db.Brand;
import com.inditex.entity.db.Product;
import com.inditex.entity.type.CurrencyType;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricesDto {
    private Brand brand;
    private Integer priority;
    private LocalDateTime startDate;
    private Double price;
    private CurrencyType curr;
    private LocalDateTime endDate;
    private Integer priceList;
    private Product product;
}
