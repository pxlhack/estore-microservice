package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PurchaseType", description = "Сервис для выполнения операций над типами покупок")
@RequestMapping("/estore/api/purchase-type")
public class PurchaseTypeController {

    @GetMapping("/test")
    @Operation(
            summary = "Тестовый метод",
            responses = {@ApiResponse(description = "Тестовая фраза")}
    )
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}