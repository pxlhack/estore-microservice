package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dto.ShopDTO;

public class ShopMapper {
    public static ShopDTO convertToDto(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setAddress(shop.getAddress());

        return shopDTO;
    }
}