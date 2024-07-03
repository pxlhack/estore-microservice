package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class CreatePurchaseDTO {
    private Long electroItemId;
    private Long employeeId;
    private Long shopId;
    private String purchaseDate;
    private Long purchaseTypeId;
}