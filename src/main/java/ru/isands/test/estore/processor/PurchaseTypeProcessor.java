package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.repo.PurchaseTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class PurchaseTypeProcessor implements FileProcessor {
    private final PurchaseTypeRepository purchaseTypeRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("PurchaseType.csv");
    }

    private void processRows(List<String[]> rows) {
        List<PurchaseType> purchaseTypes = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            String name = row[1];

            PurchaseType purchaseType = new PurchaseType();
            purchaseType.setName(name);

            purchaseTypes.add(purchaseType);
        }

        purchaseTypeRepository.saveAll(purchaseTypes);
    }
}