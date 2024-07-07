package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class PositionTypeProcessor implements FileProcessor {
    private final PositionTypeRepository positionTypeRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("PositionType.csv");
    }

    private void processRows(List<String[]> rows) {
        List<PositionType> positionTypes = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            String name = row[1];

            PositionType positionType = new PositionType();
            positionType.setName(name);

            positionTypes.add(positionType);
        }

        positionTypeRepository.saveAll(positionTypes);
    }
}