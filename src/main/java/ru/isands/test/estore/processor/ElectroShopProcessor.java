package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.ElectroShop;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ElectroShopRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class ElectroShopProcessor implements FileProcessor {
    private final ElectroShopRepository electroShopRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("ElectroShop.csv");
    }

    private void processRows(List<String[]> rows) {
        List<ElectroShop> electroShops = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(row.length + " " +  Arrays.toString(row));

            ElectroShop electroItem = convertToElectroShop(row);

            electroShops.add(electroItem);
        }

        electroShopRepository.saveAll(electroShops);
    }

    private static ElectroShop convertToElectroShop(String[] row) {
        Long shopId = Long.parseLong(row[0]);
        Long electroItemId = Long.parseLong(row[1]);
        int count = Integer.parseInt(row[2]);

        ElectroShop electroShop = new ElectroShop();

        Shop shop = new Shop();
        shop.setId(shopId);
        electroShop.setShop(shop);

        ElectroItem electroItem = new ElectroItem();
        electroItem.setId(electroItemId);
        electroShop.setElectroItem(electroItem);

        electroShop.setCount(count);

        return electroShop;
    }

}