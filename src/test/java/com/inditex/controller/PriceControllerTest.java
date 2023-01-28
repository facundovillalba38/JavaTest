package com.inditex.controller;

import com.inditex.entity.dto.PricesDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void getPriceControllerTest1() {
        PricesDto pricesDto = RestAssured.given()
                .queryParams("applicationDate", "2020-06-14 10:00:00", "productId", 35455, "brandId", 1)
                .when()
                .get("/v1/inditex/prices").then().statusCode(HttpStatus.OK.value())
                .extract()
                .as(PricesDto.class);
        assertEquals(35.5, pricesDto.getPrice());
    }

    @Test
    void getPriceControllerTest2() {
        PricesDto pricesDto = RestAssured.given()
                .queryParams("applicationDate", "2020-06-14 16:00:00", "productId", 35455, "brandId", 1)
                .when()
                .get("/v1/inditex/prices").then().statusCode(HttpStatus.OK.value())
                .extract()
                .as(PricesDto.class);
        assertEquals(25.45, pricesDto.getPrice());
    }

    @Test
    void getPriceControllerTest3() {
        PricesDto pricesDto = RestAssured.given()
                .queryParams("applicationDate", "2020-06-14 21:00:00", "productId", 35455, "brandId", 1)
                .when()
                .get("/v1/inditex/prices").then().statusCode(HttpStatus.OK.value())
                .extract()
                .as(PricesDto.class);
        assertEquals(35.5, pricesDto.getPrice());
    }

    @Test
    void getPriceControllerTest4() {
        PricesDto pricesDto = RestAssured.given()
                .queryParams("applicationDate", "2020-06-15 10:00:00", "productId", 35455, "brandId", 1)
                .when()
                .get("/v1/inditex/prices").then().statusCode(HttpStatus.OK.value())
                .extract()
                .as(PricesDto.class);
        assertEquals(30.5, pricesDto.getPrice());
    }

    @Test
    void getPriceControllerTest5() {
        PricesDto pricesDto = RestAssured.given()
                .queryParams("applicationDate", "2020-06-16 21:00:00", "productId", 35455, "brandId", 1)
                .when()
                .get("/v1/inditex/prices").then().statusCode(HttpStatus.OK.value())
                .extract()
                .as(PricesDto.class);
        assertEquals(38.95, pricesDto.getPrice());
    }
}

