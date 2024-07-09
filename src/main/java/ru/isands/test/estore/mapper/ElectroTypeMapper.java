package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dto.ElectroTypeDTO;
import ru.isands.test.estore.dto.PositionTypeDTO;

public class ElectroTypeMapper {
    public static ElectroTypeDTO convertToDto(ElectroType electroType) {
        ElectroTypeDTO electroTypeDTO = new ElectroTypeDTO();
        electroTypeDTO.setId(electroType.getId());
        electroTypeDTO.setName(electroType.getName());

        return electroTypeDTO;
    }
}