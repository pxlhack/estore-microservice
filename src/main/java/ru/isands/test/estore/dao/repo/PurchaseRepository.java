package ru.isands.test.estore.dao.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.isands.test.estore.dao.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {
    List<Purchase> findByShopId(Long shopId);
}
