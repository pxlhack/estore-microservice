package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.isands.test.estore.dao.entity.ElectroShop;
import ru.isands.test.estore.dao.entity.ElectroShopPK;

import java.util.Optional;

public interface ElectroShopRepository extends JpaRepository<ElectroShop, ElectroShopPK> {

    Optional<ElectroShop> findByElectroItemIdAndShopId(Long electroItemId, Long shopId);
}
