package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class ElectroTypeProcessor implements FileProcessor {
    private final ElectroTypeRepository electroTypeRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("ElectroType.csv");
    }

    private void processRows(List<String[]> rows) {
        List<ElectroType> electroTypes = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            String name = row[1];

            ElectroType electroType = new ElectroType();
            electroType.setName(name);

            electroTypes.add(electroType);
        }

        electroTypeRepository.saveAll(electroTypes);
    }
}