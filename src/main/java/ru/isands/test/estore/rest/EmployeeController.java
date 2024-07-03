package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateEmployeeDTO;
import ru.isands.test.estore.dto.EmployeeDTO;
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
}
