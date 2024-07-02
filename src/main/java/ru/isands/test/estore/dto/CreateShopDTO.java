package ru.isands.test.estore.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateShopDTO {
    @NotEmpty(message = "Name must not be empty")
    @Max(150)
    private String name;

    @NotNull
    private String address;
}