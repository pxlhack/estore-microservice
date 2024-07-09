package ru.isands.test.estore.dao.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.ElectroItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ElectroItemWithElectroTypeRepositoryImpl implements ElectroItemWithElectroTypeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ElectroItem> findWithElectroType(Pageable pageable) {
        TypedQuery<ElectroItem> query = entityManager.createQuery(
            "SELECT DISTINCT ei FROM ElectroItem ei JOIN FETCH ei.electroType", ElectroItem.class);
        
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        List<ElectroItem> electroItems = query.getResultList();
        
        TypedQuery<Long> countQuery = entityManager.createQuery(
            "SELECT COUNT(DISTINCT ei) FROM ElectroItem ei", Long.class);
        Long totalElements = countQuery.getSingleResult();

        return new PageImpl<>(electroItems, pageable, totalElements);
    }
}