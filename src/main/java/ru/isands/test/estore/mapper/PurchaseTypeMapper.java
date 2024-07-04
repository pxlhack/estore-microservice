package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dto.PurchaseTypeDTO;

public class PurchaseTypeMapper {
    public static PurchaseTypeDTO convertToDto(PurchaseType purchaseType) {
        PurchaseTypeDTO purchaseTypeDTO = new PurchaseTypeDTO();
        purchaseTypeDTO.setId(purchaseType.getId());
        purchaseTypeDTO.setName(purchaseType.getName());

        return purchaseTypeDTO;
    }
}