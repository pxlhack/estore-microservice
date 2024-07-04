package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.dto.*;
import ru.isands.test.estore.mapper.PositionTypeMapper;
import ru.isands.test.estore.mapper.ShopMapper;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final ShopRepository shopRepository;

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAllWithShopAndPositionType();

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

        ShopDTO shopDTO = ShopMapper.convertToDto(employee.getShop());
        PositionTypeDTO positionTypeDTO = PositionTypeMapper.convertToDto(employee.getPositionType());
        employeeDTO.setShop(shopDTO);
        employeeDTO.setPositionType(positionTypeDTO);

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

    public List<TopEmployeeBySalesCountDTO> getTopEmployeesByPositionAndSalesCount() {
        List<Object[]> results = employeeRepository.findTopEmployeesByPositionAndSalesCount();
        return results.stream()
                .map(result -> new TopEmployeeBySalesCountDTO(
                        (String) result[0],
                        ((BigInteger) result[1]).longValue(),
                        (String) result[2],
                        (String) result[3],
                        ((BigInteger) result[4]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
