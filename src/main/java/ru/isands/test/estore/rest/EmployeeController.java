package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.*;
import ru.isands.test.estore.service.EmployeeService;

import java.util.List;

@RestController
@Tag(name = "Employee", description = "Сервис для выполнения операций над сотрудниками магазина")
@RequestMapping("/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Получение списка сотрудников")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание сотрудника")
    public ResponseEntity<EmployeeDTO> create(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(createEmployeeDTO));
    }

    @GetMapping("/top-by-position/sales-count")
    @Operation(summary = "Вывод информации о лучших сотрудниках в зависимости от занимаемой должности по " +
                         "количеству проданных товаров за последний год")
    public ResponseEntity<List<TopEmployeeBySalesCountDTO>> getTopEmployeesByPositionAndSalesCount() {
        List<TopEmployeeBySalesCountDTO> employees = employeeService.getTopEmployeesByPositionAndSalesCount();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/top-by-position/sales-sum")
    @Operation(summary = "Вывод информации о лучших сотрудниках в зависимости от занимаемой должности по " +
                         "сумме проданных товаров за последний год")
    public ResponseEntity<List<TopEmployeeBySalesSumDTO>> getTopEmployeesByPositionAndSalesSum() {
        List<TopEmployeeBySalesSumDTO> employees = employeeService.getTopEmployeesByPositionAndSalesSum();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/top")
    public TopEmployeeProjection getTopEmployee(
            @RequestParam("positionName") String positionName,
            @RequestParam("electroTypeName") String electroTypeName) {
        return employeeService.getTopEmployee(positionName, electroTypeName);
    }
}