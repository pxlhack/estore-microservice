package ru.isands.test.estore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopEmployeeBySalesCountDTO {
    private String position;
    private Long employeeId;
    private String lastName;
    private String firstName;
    private Long salesCount;
}