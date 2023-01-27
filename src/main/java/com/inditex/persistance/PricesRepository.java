package com.inditex.persistance;

import com.inditex.entity.db.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query(value = "SELECT * FROM vw_API_GetProductos_Precios " +
            " WHERE dbo.vw_API_GetProductos_Precios.ProductoID = ?1"+
            " AND dbo.vw_API_GetProductos_Precios.EmpresaID = ?2" +
            " AND dbo.vw_API_GetProductos_Precios.SucursalID = ?3",
            nativeQuery = true)
    List<Prices> findPriceByDateAndProductAndBrand(LocalDateTime applicationDate, Long productId, Long brandId);

}
