package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.repo.PurchaseTypeRepository;
import ru.isands.test.estore.dto.PurchaseTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseTypeService {
    private final PurchaseTypeRepository purchaseTypeRepository;

    public List<PurchaseTypeDTO> getAll() {
        List<PurchaseType> purchaseTypes = purchaseTypeRepository.findAll();

        return purchaseTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PurchaseTypeDTO convertToDto(PurchaseType purchaseType) {
        PurchaseTypeDTO purchaseTypeDTO = new PurchaseTypeDTO();
        purchaseTypeDTO.setId(purchaseType.getId());
        purchaseTypeDTO.setName(purchaseType.getName());

        return purchaseTypeDTO;
    }


    public PurchaseTypeDTO create(String name) {
        PurchaseType purchaseType = new PurchaseType();
        purchaseType.setName(name);

        purchaseTypeRepository.save(purchaseType);

        return convertToDto(purchaseType);
    }
}