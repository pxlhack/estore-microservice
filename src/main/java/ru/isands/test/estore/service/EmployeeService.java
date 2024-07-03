package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.dto.CreateEmployeeDTO;
import ru.isands.test.estore.dto.EmployeeDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final ShopRepository shopRepository;

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setPatronymic(employee.getPatronymic());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        employeeDTO.setBirthDate(formatter.format(employee.getBirthDate()));

        employeeDTO.setPositionTypeId(employee.getPositionType().getId());
        employeeDTO.setShopId(employee.getShop().getId());
        employeeDTO.setGender(employee.isGender());

        return employeeDTO;
    }

    public EmployeeDTO create(CreateEmployeeDTO createEmployeeDTO) {
        Long positionTypeId = createEmployeeDTO.getPositionTypeId();
        PositionType positionType = positionTypeRepository.findById(positionTypeId).orElseThrow(() ->
                new RuntimeException("Position type with id = " + positionTypeId + " found"));

        Long shopId = createEmployeeDTO.getShopId();
        Shop shop = shopRepository.findById(positionTypeId).orElseThrow(() ->
                new RuntimeException("Shop with id = " + shopId + " found"));

        Employee employee = createEmployee(createEmployeeDTO, positionType, shop);

        employeeRepository.save(employee);

        return toDTO(employee);
    }

    private static Employee createEmployee(CreateEmployeeDTO createEmployeeDTO, PositionType positionType, Shop shop) {
        Employee employee = new Employee();
        employee.setLastName(createEmployeeDTO.getLastName());
        employee.setFirstName(createEmployeeDTO.getFirstName());
        employee.setPatronymic(createEmployeeDTO.getPatronymic());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(createEmployeeDTO.getBirthDate());
            employee.setBirthDate(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        employee.setPositionType(positionType);
        employee.setShop(shop);
        employee.setGender(createEmployeeDTO.getGender());
        return employee;
    }
}
