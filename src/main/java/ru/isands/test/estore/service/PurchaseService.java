package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.*;
import ru.isands.test.estore.dto.CreatePurchaseDTO;
import ru.isands.test.estore.dto.ElectroItemDTO;
import ru.isands.test.estore.dto.PurchaseDTO;
import ru.isands.test.estore.exception.BadRequestException;
import ru.isands.test.estore.exception.NotFoundException;
import ru.isands.test.estore.mapper.ElectroItemMapper;
import ru.isands.test.estore.mapper.PurchaseMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final PurchaseTypeRepository purchaseTypeRepository;
    private final ElectroItemRepository electroItemRepository;
    private final EmployeeRepository employeeRepository;
    private final ShopRepository shopRepository;
    private final ElectroShopRepository electroShopRepository;

    public List<PurchaseDTO> getAll() {
        Pageable allPageable = Pageable.unpaged();
        Page<Purchase> purchasePage = purchaseRepository.findAll(allPageable);
        List<Purchase> purchases = purchasePage.getContent();

        return purchases.stream()
                .map(PurchaseMapper::convertToDto)
                .collect(Collectors.toList());
    }


    public List<PurchaseDTO> create(CreatePurchaseDTO createPurchaseDTO) {
        Long electroItemId = createPurchaseDTO.getElectroItemId();
        Long shopId = createPurchaseDTO.getShopId();
        Long employeeId = createPurchaseDTO.getEmployeeId();
        Long purchaseTypeId = createPurchaseDTO.getPurchaseTypeId();
        int count = createPurchaseDTO.getCount();

        ElectroItem electroItem = electroItemRepository.findById(electroItemId)
                .orElseThrow(() -> new NotFoundException("Electro item with id = " + electroItemId + " not found"));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop with id = " + shopId + " not found"));

        ElectroShop electroShop = electroShopRepository.findByElectroItemIdAndShopId(electroItemId, shopId)
                .orElseThrow(() -> new BadRequestException("Electro item not found in this shop"));

        if (electroShop.getCount() < count) {
            throw new BadRequestException("There are not so many electro items in the shop");
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with id = " + employeeId + " not found"));

        PurchaseType purchaseType = purchaseTypeRepository.findById(purchaseTypeId)
                .orElseThrow(() -> new NotFoundException("Purchase type with id = " + purchaseTypeId + " not found"));

        Date purchaseDate;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            purchaseDate = formatter.parse(createPurchaseDTO.getPurchaseDate());
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format, expected dd.MM.yyyy HH:mm");
        }

        List<Purchase> purchases = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Purchase purchase = new Purchase();
            purchase.setElectroItem(electroItem);
            purchase.setShop(shop);
            purchase.setEmployee(employee);
            purchase.setPurchaseType(purchaseType);
            purchase.setPurchaseDate(purchaseDate);
            purchases.add(purchase);
        }

        List<Purchase> savedPurchases = (List<Purchase>) purchaseRepository.saveAll(purchases);

        electroShop.setCount(electroShop.getCount() - count);
        electroShopRepository.save(electroShop);

        return savedPurchases.stream()
                .map(PurchaseMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Page<PurchaseDTO> getPurchasesPerPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Purchase> purchases = purchaseRepository.findAll(pageable);

        return convertToDto(purchases);
    }

    private Page<PurchaseDTO> convertToDto(Page<Purchase> entityPage) {
        List<PurchaseDTO> dtoList = entityPage
                .getContent()
                .stream()
                .map(PurchaseMapper::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }
}