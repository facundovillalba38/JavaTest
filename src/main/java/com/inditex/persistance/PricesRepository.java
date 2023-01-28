package com.inditex.persistance;

import com.inditex.entity.db.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query(value = "SELECT * FROM PRICES WHERE BRAND_ID = ?3 " +
            "AND PRODUCT_ID = ?2 AND " +
            "?1 BETWEEN START_DATE AND END_DATE;",
            nativeQuery = true)
    List<Prices> findPriceByDateAndProductAndBrand(String applicationDate, Long productId, Long brandId);

}
