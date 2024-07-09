package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.ElectroItem;

import java.util.List;

@Repository
public interface ElectroItemRepository extends PagingAndSortingRepository<ElectroItem, Long>,
        ElectroItemWithElectroTypeRepository {

    @Query("SELECT DISTINCT ei FROM ElectroItem ei JOIN FETCH ei.electroType")
    List<ElectroItem> findAllWithElectroType();
}