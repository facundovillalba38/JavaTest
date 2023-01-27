package com.inditex.service;

import com.inditex.entity.dto.PricesDto;

import java.time.LocalDateTime;

public interface PricesService {
    PricesDto getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
