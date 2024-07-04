package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String birthDate;
    private ShopDTO shop;
    private PositionTypeDTO positionType;
    private Boolean gender;
}