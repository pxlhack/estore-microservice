package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isands.test.estore.dao.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT COALESCE(SUM(ei.price), 0) " +
           "FROM Shop shop " +
           "JOIN shop.purchases sp " +
           "JOIN sp.purchaseType pt " +
           "JOIN sp.electroItem ei " +
           "WHERE pt.name = :purchaseTypeName " +
           "AND shop.id = :shopId")
    Long getTotalPriceByPurchaseTypeAndShopId(@Param("purchaseTypeName") String purchaseTypeName, @Param("shopId") Long shopId);
}