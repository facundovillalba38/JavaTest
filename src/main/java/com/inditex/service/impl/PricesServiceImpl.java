package com.inditex.service.impl;

import com.inditex.entity.db.Brand;
import com.inditex.entity.db.Prices;
import com.inditex.entity.db.Product;
import com.inditex.entity.dto.PricesDto;
import com.inditex.exception.ApiRequestException;
import com.inditex.persistance.BrandRepository;
import com.inditex.persistance.PricesRepository;
import com.inditex.persistance.ProductRepository;
import com.inditex.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PricesServiceImpl implements PricesService {

    private PricesRepository pricesRepository;
    private BrandRepository brandRepository;
    private ProductRepository productRepository;

    @Autowired
    public PricesServiceImpl(PricesRepository pricesRepository, BrandRepository brandRepository, ProductRepository productRepository){
        this.pricesRepository = pricesRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PricesDto getPrice(String applicationDate, Long productId, Long brandId) {
        this.checkDate(applicationDate);

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ApiRequestException("No Brand found for the ID: "+brandId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException("No Product found for the ID: "+productId));

        List<Prices> pricesList = pricesRepository.findPriceByDateAndProductAndBrand(applicationDate, productId, brandId);
        if(pricesList.isEmpty()){
            throw new ApiRequestException("No Prices found for the requested parameters.");
        }
        Prices price = getHighPriority(pricesList);
        return toPricesDto(price);
    }

    private Prices getHighPriority(List<Prices> pricesList){
        return pricesList
                .stream()
                .max(Comparator.comparing(Prices::getPriority))
                .orElseThrow(NoSuchElementException::new);
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

    private void checkDate(String date){
        if(!isValidDate(date)){
            throw new ApiRequestException("Invalid date format. Must be yyyy-MM-dd HH:mm:ss");
        }
    }

    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
