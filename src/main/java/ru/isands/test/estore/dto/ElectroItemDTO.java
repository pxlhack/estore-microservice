package ru.isands.test.estore.dto;

import lombok.Data;

@Data
public class ElectroItemDTO {
    private Long id;
    private String name;
    private Long electroTypeId;
    private Long price;
    private boolean archive;
    private String description;
}