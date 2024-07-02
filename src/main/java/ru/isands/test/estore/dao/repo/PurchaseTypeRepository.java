package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.dao.entity.PurchaseType;

public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {
}