package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dto.CreateElectroItemDTO;
import ru.isands.test.estore.service.ElectroItemService;

@RestController
@Tag(name = "ElectroItem", description = "Сервис для выполнения операций над товарами")
@RequestMapping("/estore/api/electro-item")
@RequiredArgsConstructor
public class ElectroItemController {
    private final ElectroItemService electroItemService;

    @PostMapping
    public void create(CreateElectroItemDTO createElectroItemDTO) {
        electroItemService.create(createElectroItemDTO);
    }
}