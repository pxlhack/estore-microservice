package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateElectroItemDTO;
import ru.isands.test.estore.dto.ElectroItemDTO;
import ru.isands.test.estore.service.ElectroItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getElectroItemsPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ElectroItemDTO> pageElectroItems = electroItemService.getElectroItemsPerPage(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("items", pageElectroItems.getContent());
        response.put("currentPage", pageElectroItems.getNumber());
        response.put("totalItems", pageElectroItems.getTotalElements());
        response.put("totalPages", pageElectroItems.getTotalPages());

        return ResponseEntity.ok(response);
    }
}