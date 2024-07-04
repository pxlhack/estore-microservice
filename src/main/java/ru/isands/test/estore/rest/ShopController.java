package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateShopDTO;
import ru.isands.test.estore.dto.ShopDTO;
import ru.isands.test.estore.service.ShopService;

import java.util.List;

@RestController
@Tag(name = "Shop", description = "Сервис для выполнения операций над магазинами")
@RequestMapping("/estore/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;


    @GetMapping
    @Operation(summary = "Получение списка магазинов")
    public ResponseEntity<List<ShopDTO>> getAll() {
        return ResponseEntity.ok(shopService.getAll());
    }

    @PostMapping
    @Operation(summary = "Создание магазина")
    public ResponseEntity<ShopDTO> create(@RequestBody CreateShopDTO createShopDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shopService.create(createShopDTO));
    }

    @GetMapping("/{id}/sales-sum-for-cash")
    @Operation(summary = "Вывод суммы денежных средств, полученной магазином через оплату наличными")
    public ResponseEntity<Long> getTotalPriceForCashPurchases(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.getTotalPriceForCashPurchases(id));
    }
}