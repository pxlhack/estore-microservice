package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreatePurchaseDTO;
import ru.isands.test.estore.dto.ElectroItemDTO;
import ru.isands.test.estore.dto.PurchaseDTO;
import ru.isands.test.estore.service.PurchaseService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Purchase", description = "Сервис для выполнения операций над покупками")
@RequestMapping("/estore/api/purchase")
@RequiredArgsConstructor
@Validated
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    @Operation(summary = "Получение списка покупок")
    public ResponseEntity<List<PurchaseDTO>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание покупки")
    public ResponseEntity<List<PurchaseDTO>> create(@Valid @RequestBody CreatePurchaseDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseService.create(dto));
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getPurchasesPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PurchaseDTO> pageElectroItems = purchaseService.getPurchasesPerPage(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("items", pageElectroItems.getContent());
        response.put("currentPage", pageElectroItems.getNumber());
        response.put("totalItems", pageElectroItems.getTotalElements());
        response.put("totalPages", pageElectroItems.getTotalPages());

        return ResponseEntity.ok(response);
    }
}