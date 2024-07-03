package ru.isands.test.estore.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class CreateEmployeeDTO {
    @NotNull
    @Max(100)
    private String lastName;

    @NotNull
    @Max(100)
    private String firstName;

    @NotNull
    @Max(100)
    private String patronymic;

    @NotNull
    private String birthDate;

    @NotNull
    private Long positionTypeId;

    @NotNull
    private Long shopId;

    @NotNull
    private Boolean gender;
}
