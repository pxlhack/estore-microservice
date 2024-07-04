package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dto.PurchaseDTO;

import java.text.SimpleDateFormat;

public class PurchaseMapper {
    public static PurchaseDTO convertToDto(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();

        dto.setId(purchase.getId());
        dto.setElectroItemId(purchase.getElectroItem().getId());
        dto.setEmployeeId(purchase.getEmployee().getId());
        dto.setShopId(purchase.getShop().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dto.setPurchaseDate(formatter.format(purchase.getPurchaseDate()));

        dto.setPurchaseTypeId(purchase.getPurchaseType().getId());

        dto.setShop(ShopMapper.convertToDto(purchase.getShop()));
        dto.setPurchaseType(PurchaseTypeMapper.convertToDto(purchase.getPurchaseType()));
        dto.setElectroItem(ElectroItemMapper.convertToDto(purchase.getElectroItem()));

        return dto;
    }
}