package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;
import ru.isands.test.estore.dto.CreateElectroItemDTO;

@Service
@RequiredArgsConstructor
public class ElectroItemService {
    private final ElectroItemRepository electroItemRepository;
    private final ElectroTypeRepository electroTypeRepository;

    public void create(CreateElectroItemDTO createElectroItemDTO) {
        Long electroTypeId = createElectroItemDTO.getElectroTypeId();
        ElectroType electroType = electroTypeRepository.findById(electroTypeId).orElseThrow(() ->
                new RuntimeException("ElectroType with id = " + electroTypeId + " Not found"));

        ElectroItem electroItem = new ElectroItem();
        electroItem.setName(createElectroItemDTO.getName());
        electroItem.setElectroType(electroType);
        electroItem.setPrice(createElectroItemDTO.getPrice());
        electroItem.setCount(createElectroItemDTO.getCount());
        electroItem.setArchive(false);
        electroItem.setDescription(createElectroItemDTO.getDescription());

        electroItemRepository.save(electroItem);
    }
}