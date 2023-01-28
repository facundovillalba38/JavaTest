package com.inditex.service;

import com.inditex.entity.db.Brand;
import com.inditex.entity.db.Prices;
import com.inditex.entity.db.Product;
import com.inditex.entity.dto.PricesDto;
import com.inditex.entity.type.CurrencyType;
import com.inditex.exception.ApiRequestException;
import com.inditex.persistance.BrandRepository;
import com.inditex.persistance.PricesRepository;
import com.inditex.persistance.ProductRepository;
import com.inditex.service.impl.PricesServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PricesServiceImplTest {

    @Mock
    private PricesRepository pricesRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PricesServiceImpl pricesService;

    @Test
    public void getPriceTest() {
        // Given
        String applicationDate = "2020-06-14 00:00:00";
        Long productId = 1L;
        Long brandId = 1L;
        Product product = Product.builder().id(productId).build();
        Brand brand = Brand.builder().id(brandId).build();
        Prices prices = Prices.builder()
                .brand(brand)
                .priority(1)
                .startDate(LocalDateTime.now())
                .price(Double.valueOf(10))
                .curr(CurrencyType.EUR)
                .endDate(LocalDateTime.now())
                .priceList(1)
                .product(product)
                .build();
        List<Prices> pricesList = Arrays.asList(prices);
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(pricesRepository.findPriceByDateAndProductAndBrand(applicationDate, productId, brandId)).thenReturn(pricesList);

        // When
        PricesDto result = pricesService.getPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brand, result.getBrand());
        assertEquals(1, result.getPriority());
        assertEquals(CurrencyType.EUR, result.getCurr());
        assertEquals(1, result.getPriceList());
        assertEquals(product, result.getProduct());
    }

    @Test
    public void getPriceInvalidDateFormatTest() {
        // Given
        String applicationDate = "2020-06-14";
        Long productId = 1L;
        Long brandId = 1L;

        // When and Then
        assertThrows(ApiRequestException.class, () -> pricesService.getPrice(applicationDate, productId, brandId));
    }

    @Test
    public void getPriceNoBrandFoundTest() {
        // Given
        String applicationDate = "2020-06-14 00:00:00";
        Long productId = 1L;
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ApiRequestException.class, () -> pricesService.getPrice(applicationDate, productId, brandId));
    }

    @Test
    public void getPriceNoProductFoundTest() {
        // Given
        String applicationDate = "2020-06-14 00:00:00";
        Long productId = 1L;
        Long brandId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(ApiRequestException.class, () -> pricesService.getPrice(applicationDate, productId, brandId));
    }

    @Test
    public void getPriceNoPricesFoundTest() {
        // Given
        String applicationDate = "2020-06-14 00:00:00";
        Long productId = 1L;
        Long brandId = 1L;
        Product product = Product.builder().id(productId).build();
        Brand brand = Brand.builder().id(brandId).build();

        List<Prices> pricesList = Collections.emptyList();

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(pricesRepository.findPriceByDateAndProductAndBrand(applicationDate, productId, brandId)).thenReturn(pricesList);

        // When and Then
        assertThrows(ApiRequestException.class, () -> pricesService.getPrice(applicationDate, productId, brandId));
    }
}