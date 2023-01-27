package com.inditex.service.impl;

import com.inditex.entity.db.Prices;
import com.inditex.entity.dto.PricesDto;
import com.inditex.persistance.BrandRepository;
import com.inditex.persistance.PricesRepository;
import com.inditex.persistance.ProductRepository;
import com.inditex.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PricesServiceImpl implements PricesService {

    private PricesRepository pricesRepository;

    @Autowired
    public PricesServiceImpl(PricesRepository pricesRepository){
        this.pricesRepository = pricesRepository;
    }

    @Override
    public PricesDto getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        List<Prices> pricesList = pricesRepository.findPriceByDateAndProductAndBrand(applicationDate, productId, brandId);
        Prices price = pricesList
                .stream()
                .max(Comparator.comparing(Prices::getPriority))
                .orElseThrow(NoSuchElementException::new);
        return toPricesDto(price);
    }

    private PricesDto toPricesDto(Prices price){
        return PricesDto.builder()
                .brand(price.getBrand())
                .priority(price.getPriority())
                .startDate(price.getStartDate())
                .price(price.getPrice())
                .curr(price.getCurr())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .product(price.getProduct())
                .build();
    }
}
