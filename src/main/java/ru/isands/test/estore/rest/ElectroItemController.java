package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateElectroItemDTO;
import ru.isands.test.estore.dto.ElectroItemDTO;
import ru.isands.test.estore.service.ElectroItemService;

import java.util.List;

@RestController
@Tag(name = "ElectroItem", description = "Сервис для выполнения операций над товарами")
@RequestMapping("/estore/api/electro-item")
@RequiredArgsConstructor
public class ElectroItemController {
    private final ElectroItemService electroItemService;


    @GetMapping
    @Operation(summary = "Получение списка товаров")
    public ResponseEntity<List<ElectroItemDTO>> getAll() {
        return ResponseEntity.ok(electroItemService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание товара")
    public ResponseEntity<ElectroItemDTO> create(@RequestBody CreateElectroItemDTO createElectroItemDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(electroItemService.create(createElectroItemDTO));
    }

}