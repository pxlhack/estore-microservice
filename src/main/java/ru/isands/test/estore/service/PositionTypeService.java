package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;
import ru.isands.test.estore.dto.CreatePositionTypeDTO;
import ru.isands.test.estore.dto.PositionTypeDTO;
import ru.isands.test.estore.mapper.PositionTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionTypeService {
    private final PositionTypeRepository positionTypeRepository;

    public List<PositionTypeDTO> getAll() {
        List<PositionType> positionTypes = positionTypeRepository.findAll();

        return positionTypes.stream()
                .map(PositionTypeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public PositionTypeDTO create(CreatePositionTypeDTO createPositionTypeDTO) {
        PositionType positionType = new PositionType();
        positionType.setName(createPositionTypeDTO.getName());

        positionTypeRepository.save(positionType);

        return PositionTypeMapper.convertToDto(positionType);
    }
}