package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dto.TopEmployeeProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e " +
           "JOIN FETCH e.shop " +
           "JOIN FETCH e.positionType")
    List<Employee> findAllWithShopAndPositionType();

    @Query(value =
            "SELECT DISTINCT pt.name                                                           as position, " +
            "   FIRST_VALUE(e.id) OVER (PARTITION BY pt.name ORDER BY COUNT(p.id) DESC)        as employee_id, " +
            "   FIRST_VALUE(e.lastname) OVER (PARTITION BY pt.name ORDER BY COUNT(p.id) DESC)  as lastname, " +
            "   FIRST_VALUE(e.firstname) OVER (PARTITION BY pt.name ORDER BY COUNT(p.id) DESC) as firstname, " +
            "   FIRST_VALUE(COUNT(p.id)) OVER (PARTITION BY pt.name ORDER BY COUNT(p.id) DESC) as sales_count " +
            "FROM store_purchase p " +
            "   JOIN store_employee e ON p.employee_id = e.id " +
            "   JOIN position_type pt ON pt.id = e.position_id " +
            "WHERE p.purchase_date >= CURRENT_DATE - interval '1 year' " +
            "GROUP BY position, e.id, e.lastname, e.firstname " +
            "ORDER BY position;",
            nativeQuery = true)
    List<Object[]> findTopEmployeesByPositionAndSalesCount();

    @Query(value =
            "SELECT DISTINCT pt.name                                                               as position, " +
            "   FIRST_VALUE(e.id) OVER (PARTITION BY pt.name ORDER BY SUM(ei.price) DESC)          as employee_id, " +
            "   FIRST_VALUE(e.lastname) OVER (PARTITION BY pt.name ORDER BY SUM(ei.price) DESC)    as lastname, " +
            "   FIRST_VALUE(e.firstname) OVER (PARTITION BY pt.name ORDER BY SUM(ei.price) DESC)   as firstname, " +
            "   FIRST_VALUE(SUM(ei.price)) OVER (PARTITION BY pt.name ORDER BY SUM(ei.price) DESC) as sales_sum " +
            "FROM store_purchase p " +
            "   JOIN store_employee e ON p.employee_id = e.id " +
            "   JOIN position_type pt ON pt.id = e.position_id " +
            "   JOIN electro_item ei ON p.electro_item_id = ei.id " +
            "WHERE p.purchase_date >= CURRENT_DATE - interval '1 year' " +
            "GROUP BY position, e.id, e.lastname, e.firstname " +
            "ORDER BY position;",
            nativeQuery = true)
    List<Object[]> findTopEmployeesByPositionAndSalesSum();


    List<Employee> findByShop(Shop shop);

    @Query(value = "SELECT e.id AS employeeId, e.lastname AS lastname, e.firstname AS firstname, COUNT(sp.id) AS salesCount " +
                   "FROM store_employee e " +
                   "JOIN position_type pt ON e.position_id = pt.id " +
                   "JOIN store_purchase sp ON e.id = sp.employee_id " +
                   "JOIN electro_item ei ON ei.id = sp.electro_item_id " +
                   "JOIN electro_type et ON et.id = ei.electro_type_id " +
                   "WHERE pt.name = :positionName " +
                   "AND et.name = :electroTypeName " +
                   "GROUP BY e.id, e.lastname, e.firstname " +
                   "ORDER BY salesCount DESC " +
                   "LIMIT 1", nativeQuery = true)
    Optional<TopEmployeeProjection> findTopEmployeeByPositionAndElectroType(
            @Param("positionName") String positionName,
            @Param("electroTypeName") String electroTypeName);

}