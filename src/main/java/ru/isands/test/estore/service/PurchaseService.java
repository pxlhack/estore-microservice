package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.*;
import ru.isands.test.estore.dto.CreatePurchaseDTO;
import ru.isands.test.estore.dto.PurchaseDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<PurchaseDTO> getAll() {
        List<Purchase> purchaseTypes = purchaseRepository.findAll();

        return purchaseTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PurchaseDTO convertToDto(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();

        dto.setId(purchase.getId());
        dto.setElectroItemId(purchase.getElectroItem().getId());
        dto.setEmployeeId(purchase.getEmployee().getId());
        dto.setShopId(purchase.getShop().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dto.setPurchaseDate(formatter.format(purchase.getPurchaseDate()));

        dto.setPurchaseTypeId(purchase.getPurchaseType().getId());

        return dto;
    }


    public PurchaseDTO create(CreatePurchaseDTO createPurchaseDTO) {
        Purchase purchase = new Purchase();

        Long electroItemId = createPurchaseDTO.getElectroItemId();
        ElectroItem electroItem = electroItemRepository.findById(electroItemId)
                .orElseThrow(() ->
                        new RuntimeException("Electro item with id = " + electroItemId + " not found"));
        purchase.setElectroItem(electroItem);

        Long shopId = createPurchaseDTO.getShopId();
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() ->
                        new RuntimeException("Shop with id = " + shopId + " not found"));
        purchase.setShop(shop);

        Long employeeId = createPurchaseDTO.getEmployeeId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee with id = " + employeeId + " not found"));
        purchase.setEmployee(employee);

        Long purchaseTypeId = createPurchaseDTO.getPurchaseTypeId();
        PurchaseType purchaseType = purchaseTypeRepository.findById(purchaseTypeId)
                .orElseThrow(() ->
                        new RuntimeException("Purchase type with id = " + purchaseTypeId + " not found"));
        purchase.setPurchaseType(purchaseType);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(createPurchaseDTO.getPurchaseDate());
            purchase.setPurchaseDate(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        purchaseRepository.save(purchase);

        return convertToDto(purchase);
    }
}