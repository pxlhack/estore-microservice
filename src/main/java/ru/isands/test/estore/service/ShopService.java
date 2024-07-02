package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.dto.CreateShopDTO;
import ru.isands.test.estore.dto.ShopDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<ShopDTO> getAll() {
        List<Shop> purchaseTypes = shopRepository.findAll();

        return purchaseTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ShopDTO convertToDto(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setAddress(shop.getAddress());

        return shopDTO;
    }


    public ShopDTO create(CreateShopDTO createShopDTO) {
        Shop shop = new Shop();
        shop.setName(createShopDTO.getName());
        shop.setAddress(createShopDTO.getAddress());

        shopRepository.save(shop);

        return convertToDto(shop);
    }

}
