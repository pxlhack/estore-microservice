package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dto.CreateShopDTO;
import ru.isands.test.estore.dto.EmployeeDTO;
import ru.isands.test.estore.dto.ShopDTO;
import ru.isands.test.estore.service.EmployeeService;
import ru.isands.test.estore.service.ShopService;

import java.util.List;

@RestController
@Tag(name = "Shop", description = "Сервис для выполнения операций над магазинами")
@RequestMapping("/estore/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final EmployeeService employeeService;

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

    @PostMapping("/{id}/electro-item")
    @Operation(summary = "Добавление товара в магазин")
    public ResponseEntity<String> addElectroItem(@PathVariable Long id,
                                                 @RequestParam("electroItemId") Long electroItemId,
                                                 @RequestParam("count") int count) {

        String response = shopService.addElectroItem(id, electroItemId, count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/sales-sum-for-cash")
    @Operation(summary = "Вывод суммы денежных средств, полученной магазином через оплату наличными")
    public ResponseEntity<Long> getTotalPriceForCashPurchases(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.getTotalPriceForCashPurchases(id));
    }


    @GetMapping("/{id}/employee")
    @Operation(summary = "Получение списка сотрудников магазина")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getByShopId(id));
    }
}