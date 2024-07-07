package ru.isands.test.estore.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.entity.ElectroEmployee;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.repo.ElectroEmployeeRepository;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;
import ru.isands.test.estore.dao.repo.EmployeeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.isands.test.estore.util.CsvFileHelper.isCsvFile;
import static ru.isands.test.estore.util.CsvFileHelper.processCsvFile;

@Component
@RequiredArgsConstructor
public class ElectroEmployeeProcessor implements FileProcessor {
    private final ElectroEmployeeRepository electroEmployeeRepository;
    private final ElectroTypeRepository electroTypeRepository;
    private final EmployeeRepository employeeRepository;

    public boolean process(byte[] content, String fileName) {
        System.out.println("tut");
        return processCsvFile(content, this::processRows);
    }

    @Override
    public boolean canProcess(String fileName) {
        return isCsvFile(fileName) && fileName.equals("ElectroEmployee.csv");
    }

    private void processRows(List<String[]> rows) {
        List<ElectroEmployee> electroEmployees = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            System.out.println(row.length + " " + Arrays.toString(row));

            ElectroEmployee electroEmployee = convertToElectroEmployee(row);

            electroEmployees.add(electroEmployee);
        }

        electroEmployeeRepository.saveAll(electroEmployees);
    }

    private ElectroEmployee convertToElectroEmployee(String[] row) {
        Long electroTypeId = Long.parseLong(row[1]);
        Long employeeId = Long.parseLong(row[0 ]);

        ElectroEmployee electroEmployee = new ElectroEmployee();

        ElectroType electroType = electroTypeRepository.findById(electroTypeId)
                .orElseThrow(() -> new RuntimeException("ElectroType with id " + electroTypeId + " not found"));
        electroEmployee.setElectroType(electroType);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));
        electroEmployee.setEmployee(employee);

        return electroEmployee;
    }
}