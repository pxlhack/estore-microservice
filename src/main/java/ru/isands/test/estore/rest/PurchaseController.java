package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreatePurchaseDTO;
import ru.isands.test.estore.dto.PurchaseDTO;
import ru.isands.test.estore.service.PurchaseService;

import java.util.List;

@RestController
@Tag(name = "Purchase", description = "Сервис для выполнения операций над покупками")
@RequestMapping("/estore/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    @Operation(summary = "Получение списка покупок")
    public ResponseEntity<List<PurchaseDTO>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание покупки")
    public ResponseEntity<List<PurchaseDTO>> create(@RequestBody CreatePurchaseDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseService.create(dto));
    }
}