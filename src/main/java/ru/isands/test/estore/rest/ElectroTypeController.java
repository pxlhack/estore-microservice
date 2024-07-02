package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateElectroTypeDTO;
import ru.isands.test.estore.dto.ElectroTypeDTO;
import ru.isands.test.estore.service.ElectroTypeService;

import java.util.List;

@RestController
@Tag(name = "ElectroType", description = "Сервис для выполнения операций над типами электроники")
@RequestMapping("/estore/api/electro-type")
@RequiredArgsConstructor
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;

    @GetMapping
    @Operation(summary = "Получение списка типов электроники")
    public ResponseEntity<List<ElectroTypeDTO>> getAll() {
        return ResponseEntity.ok(electroTypeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание типа электроники")
    public ResponseEntity<ElectroTypeDTO> create(@RequestBody CreateElectroTypeDTO createElectroTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(electroTypeService.create(createElectroTypeDTO));
    }
}