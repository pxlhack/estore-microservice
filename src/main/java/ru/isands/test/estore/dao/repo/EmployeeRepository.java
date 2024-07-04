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
	
}