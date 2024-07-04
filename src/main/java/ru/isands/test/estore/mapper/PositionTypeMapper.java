package ru.isands.test.estore.mapper;

import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dto.PositionTypeDTO;

public class PositionTypeMapper {
    public static PositionTypeDTO convertToDto(PositionType positionType) {
        PositionTypeDTO positionTypeDTO = new PositionTypeDTO();
        positionTypeDTO.setId(positionType.getId());
        positionTypeDTO.setName(positionType.getName());

        return positionTypeDTO;
    }
}