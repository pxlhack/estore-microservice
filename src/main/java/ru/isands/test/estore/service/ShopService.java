package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.dto.CreateShopDTO;
import ru.isands.test.estore.dto.ShopDTO;
import ru.isands.test.estore.mapper.ShopMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();

        return shops.stream()
                .map(ShopMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ShopDTO create(CreateShopDTO createShopDTO) {
        Shop shop = new Shop();
        shop.setName(createShopDTO.getName());
        shop.setAddress(createShopDTO.getAddress());

        shopRepository.save(shop);

        return ShopMapper.convertToDto(shop);
    }

    public Long getTotalPriceForCashPurchases(Long shopId) {
        return shopRepository.getTotalPriceByPurchaseTypeAndShopId("Наличные", shopId);
    }
}
