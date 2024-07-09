package ru.isands.test.estore.dao.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.ElectroItem;

public interface ElectroItemWithElectroTypeRepository {
    Page<ElectroItem> findWithElectroType(Pageable pageable);
}