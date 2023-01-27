package com.inditex.controller;

import com.inditex.entity.dto.PricesDto;
import com.inditex.service.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/inditex/prices")
public class PricesController {

    private PricesService pricesService;

    @Autowired
    public PricesController(PricesService pricesService){
        this.pricesService = pricesService;
    }

    @GetMapping("/")
    @Operation(summary = "Get Prices for an specific Application Date, Product and Brand.")
    public ResponseEntity<PricesDto> getPrice(
            @Parameter(description = "Application Date", example = "2020-06-14 00:00:00")
                    @RequestParam LocalDateTime applicationDate,
            @Parameter(description = "Product Id", example = "3455")
                    @RequestParam Long productId,
            @Parameter(description = "Brand Id", example = "1")
                    @RequestParam Long brandId
            ){
        return new ResponseEntity(pricesService.getPrice(applicationDate, productId, brandId), HttpStatus.OK);
    }



}
