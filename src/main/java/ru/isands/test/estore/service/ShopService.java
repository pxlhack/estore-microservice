package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.ElectroShop;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;
import ru.isands.test.estore.dao.repo.ElectroShopRepository;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.dto.CreateShopDTO;
import ru.isands.test.estore.dto.ShopDTO;
import ru.isands.test.estore.exception.NotFoundException;
import ru.isands.test.estore.mapper.ShopMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ElectroShopRepository electroShopRepository;
    private final ElectroItemRepository electroItemRepository;

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

    public String addElectroItem(Long shopId, Long electroItemId, int count) {

        Optional<ElectroShop> electroShopOptional = electroShopRepository
                .findByElectroItemIdAndShopId(electroItemId, shopId);

        if (electroShopOptional.isPresent()) {
            ElectroShop electroShop = electroShopOptional.get();
            electroShop.setCount(electroShop.getCount() + count);

            electroShopRepository.save(electroShop);

        } else {
            Shop shop = shopRepository.findById(shopId)
                    .orElseThrow(() ->
                            new NotFoundException("Shop with id = " + shopId + " not found"));

            ElectroItem electroItem = electroItemRepository.findById(electroItemId)
                    .orElseThrow(() ->
                            new NotFoundException("Electro item with id = " + electroItemId + " not found"));

            ElectroShop electroShop = new ElectroShop();
            electroShop.setShop(shop);
            electroShop.setElectroItem(electroItem);
            electroShop.setCount(count);
            electroShopRepository.save(electroShop);

        }

        return "Electro item successfully added/updated in the shop.";

    }
}
