package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ShopRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class ShopProcessor implements FileProcessor {
    private final ShopRepository shopRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("Shop.csv");
    }

    private void processRows(List<String[]> rows) {
        List<Shop> shops = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(Arrays.toString(row));
            String name = row[1];
            String address = row[2];

            Shop shop = new Shop();
            shop.setName(name);
            shop.setAddress(address);

            shops.add(shop);
        }

        shopRepository.saveAll(shops);
    }
}