package ru.isands.test.estore.dao.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("select e from Employee e where e.lastName = ?1 and e.firstName = ?2 and e.patronymic = ?3 and e.birthDate = ?4")
	public Employee findFullName(String lastName, String firstName, String patronymic, Date birthDate);
	
	List<Employee> findByPositionTypeId(Long positionId);

	@Query("SELECT e FROM Employee e " +
		   "JOIN FETCH e.shop " +
		   "JOIN FETCH e.positionType")
	List<Employee> findAllWithShopAndPositionType();

	@Query(value =
			"SELECT " +
			"    pt.name as position, " +
			"    e.id as employee_id, " +
			"    e.lastname, " +
			"    e.firstname, " +
			"    COUNT(p.id) as sales_count " +
			"FROM " +
			"    store_purchase p " +
			"    JOIN public.store_employee e ON p.employee_id = e.id " +
			"    JOIN public.position_type pt ON pt.id = e.position_id " +
			"    JOIN public.electro_item ei ON p.electro_item_id = ei.id " +
			"WHERE " +
			"    p.purchase_date >= CURRENT_DATE - interval '1 year' " +
			"GROUP BY " +
			"    pt.id, e.id " +
			"ORDER BY " +
			"    pt.name, sales_count DESC",
			nativeQuery = true)
	List<Object[]> findTopEmployeesByPositionAndSalesCount();
	
}