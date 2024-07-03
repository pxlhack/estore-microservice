package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class CreateElectroItemDTO {
    private String name;
    private Long electroTypeId;
    private Long price;
    private String description;
}