package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dto.ElectroItemDTO;

public class ElectroItemMapper {
    public static ElectroItemDTO convertToDto(ElectroItem electroItem) {
        ElectroItemDTO electroItemDTO = new ElectroItemDTO();
        electroItemDTO.setId(electroItem.getId());
        electroItemDTO.setName(electroItem.getName());
        electroItemDTO.setElectroTypeId(electroItem.getElectroType().getId());
        electroItemDTO.setPrice(electroItem.getPrice());
        electroItemDTO.setArchive(electroItem.isArchive());
        electroItemDTO.setDescription(electroItem.getDescription());

        return electroItemDTO;
    }

}