package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;
import ru.isands.test.estore.dto.CreateElectroItemDTO;
import ru.isands.test.estore.dto.ElectroItemDTO;
import ru.isands.test.estore.exception.NotFoundException;
import ru.isands.test.estore.mapper.ElectroItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectroItemService {
    private final ElectroItemRepository electroItemRepository;
    private final ElectroTypeRepository electroTypeRepository;

    public List<ElectroItemDTO> getAll() {
        List<ElectroItem> electroItems = electroItemRepository.findAll();

        return electroItems.stream()
                .map(ElectroItemMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ElectroItemDTO create(CreateElectroItemDTO createElectroItemDTO) {
        Long electroTypeId = createElectroItemDTO.getElectroTypeId();
        ElectroType electroType = electroTypeRepository.findById(electroTypeId).orElseThrow(() ->
                new NotFoundException("ElectroType with id = " + electroTypeId + " not found"));

        ElectroItem electroItem = new ElectroItem();
        electroItem.setName(createElectroItemDTO.getName());
        electroItem.setElectroType(electroType);
        electroItem.setPrice(createElectroItemDTO.getPrice());
        electroItem.setArchive(false);
        electroItem.setDescription(createElectroItemDTO.getDescription());

        electroItemRepository.save(electroItem);

        return ElectroItemMapper.convertToDto(electroItem);
    }

}