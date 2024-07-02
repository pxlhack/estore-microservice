package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.PurchaseTypeDTO;
import ru.isands.test.estore.service.PurchaseTypeService;

import java.util.List;

@RestController
@Tag(name = "PurchaseType", description = "Сервис для выполнения операций над типами покупок")
@RequestMapping("/estore/api/purchase-type")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping("/test")
    @Operation(
            summary = "Тестовый метод",
            responses = {@ApiResponse(description = "Тестовая фраза")}
    )
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @GetMapping
    @Operation(summary = "Получение списка типов покупок")
    public ResponseEntity<List<PurchaseTypeDTO>> getAll() {
        return ResponseEntity.ok(purchaseTypeService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание типа покупки")
    public ResponseEntity<PurchaseTypeDTO> create(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseTypeService.create(name));
    }
}