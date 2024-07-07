package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
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
public class EmployeeProcessor implements FileProcessor {
    private final EmployeeRepository employeeRepository;

    public boolean process(byte[] content, String fileName) {
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("Employee.csv");
    }

    private void processRows(List<String[]> rows) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(row.length + " " + Arrays.toString(row));

            Employee employee = convertToEmployee(row);

            employees.add(employee);
        }

        employeeRepository.saveAll(employees);
    }

    private static Employee convertToEmployee(String[] row) {
        String lastName = row[1];
        String firstName = row[2];
        String patronymic = row[3];
        String birthDateString = row[4];
        Long positionTypeId = Long.parseLong(row[5]);
        Long shopId = Long.parseLong(row[6]);
        boolean gender = "1".equals(row[7]);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateString);
        } catch (ParseException e) {
            throw new BadRequestException("Invalid date format, expected dd.MM.yyyy");
        }

        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setFirstName(firstName);
        employee.setPatronymic(patronymic);
        employee.setBirthDate(birthDate);

        PositionType positionType = new PositionType();
        positionType.setId(positionTypeId);
        employee.setPositionType(positionType);

        Shop shop = new Shop();
        shop.setId(shopId);
        employee.setShop(shop);

        employee.setGender(gender);

        return employee;
    }

}