package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;
import ru.isands.test.estore.dto.CreateElectroTypeDTO;
import ru.isands.test.estore.dto.ElectroTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectroTypeService {
    private final ElectroTypeRepository electroTypeRepository;

    public List<ElectroTypeDTO> getAll() {
        List<ElectroType> electroTypes = electroTypeRepository.findAll();

        return electroTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ElectroTypeDTO convertToDto(ElectroType electroType) {
        ElectroTypeDTO electroTypeDTO = new ElectroTypeDTO();
        electroTypeDTO.setId(electroType.getId());
        electroTypeDTO.setName(electroType.getName());

        return electroTypeDTO;
    }


    public ElectroTypeDTO create(CreateElectroTypeDTO createElectroTypeDTO) {
        ElectroType electroType = new ElectroType();
        electroType.setName(createElectroTypeDTO.getName());

        electroTypeRepository.save(electroType);

        return convertToDto(electroType);
    }
}
