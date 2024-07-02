package ru.isands.test.estore.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
public class CreatePurchaseTypeDTO {
    @NotEmpty(message = "Name must not be empty")
    @Max(150)
    private String name;
}