package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreatePositionTypeDTO;
import ru.isands.test.estore.dto.PositionTypeDTO;
import ru.isands.test.estore.service.PositionTypeService;

import java.util.List;

@RestController
@Tag(name = "PositionType", description = "Сервис для выполнения операций над должностями")
@RequestMapping("/estore/api/position-type")
@RequiredArgsConstructor
public class PositionTypeController {

    private final PositionTypeService positionTypeService;

    @GetMapping
    @Operation(summary = "Получение списка должностей")
    public ResponseEntity<List<PositionTypeDTO>> getAll() {
        return ResponseEntity.ok(positionTypeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание должности")
    public ResponseEntity<PositionTypeDTO> create(@RequestBody CreatePositionTypeDTO createPositionTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionTypeService.create(createPositionTypeDTO));
    }
}