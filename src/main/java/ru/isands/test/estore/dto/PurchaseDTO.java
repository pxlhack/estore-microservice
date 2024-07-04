package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class PurchaseDTO {
    private Long id;
    private Long electroItemId;
    private Long employeeId;
    private Long shopId;
    private String purchaseDate;
    private Long purchaseTypeId;

    private ElectroItemDTO electroItem;
    private ShopDTO shop;
    private PurchaseTypeDTO purchaseType;
}