package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class ElectroItemProcessor implements FileProcessor {
    private final ElectroItemRepository electroItemRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("ElectroItem.csv");
    }

    private void processRows(List<String[]> rows) {
        List<ElectroItem> electroItems = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(row.length + " " +  Arrays.toString(row));

            String name = row[1];
            Long electroTypeId = Long.valueOf(row[2]);
            Long price = Long.valueOf(row[3]);
            boolean archive = row[5].equals("1");
            String description = row[6];

            ElectroItem electroItem = new ElectroItem();

            electroItem.setName(name);

            ElectroType electroType = new ElectroType();
            electroType.setId(electroTypeId);
            electroItem.setElectroType(electroType);

            electroItem.setPrice(price);
            electroItem.setArchive(archive);
            electroItem.setDescription(description);

            electroItems.add(electroItem);
        }

        electroItemRepository.saveAll(electroItems);
    }
}