package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.PurchaseRepository;
import ru.isands.test.estore.exception.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class PurchaseProcessor implements FileProcessor {
    private final PurchaseRepository purchaseRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("Purchase.csv");
    }

    private void processRows(List<String[]> rows) {
        List<Purchase> purchases = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(row.length + " " +  Arrays.toString(row));

            Purchase purchase = convertToPurchase(row);

            purchases.add(purchase);
        }

        purchaseRepository.saveAll(purchases);
    }

    private static Purchase convertToPurchase(String[] row) {
        Long electroItemId = Long.parseLong(row[1]);
        Long employeeId = Long.parseLong(row[2]);
        Long shopId = Long.parseLong(row[5]);
        Date purchaseDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            purchaseDate = dateFormat.parse(row[3]);
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format, expected dd.MM.yyyy HH:mm");
        }
        Long purchaseTypeId = Long.parseLong(row[4]);

        Purchase purchase = new Purchase();

        ElectroItem electroItem = new ElectroItem();
        electroItem.setId(electroItemId);
        purchase.setElectroItem(electroItem);

        Employee employee = new Employee();
        employee.setId(employeeId);
        purchase.setEmployee(employee);

        Shop shop = new Shop();
        shop.setId(shopId);
        purchase.setShop(shop);

        purchase.setPurchaseDate(purchaseDate);

        PurchaseType purchaseType = new PurchaseType();
        purchaseType.setId(purchaseTypeId);
        purchase.setPurchaseType(purchaseType);

        return purchase;
    }

}