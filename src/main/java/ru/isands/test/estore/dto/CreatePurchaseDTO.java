package ru.isands.test.estore.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class CreatePurchaseDTO {

    @NotNull(message = "ID товара не может быть пустым")
    @Positive(message = "ID товара должен быть положительным числом")
    private Long electroItemId;

    @NotNull(message = "ID сотрудника не может быть пустым")
    @Positive(message = "ID сотрудника должен быть положительным числом")
    private Long employeeId;

    @NotNull(message = "ID магазина не может быть пустым")
    @Positive(message = "ID магазина должен быть положительным числом")
    private Long shopId;

    @NotNull(message = "Дата покупки не может быть пустой")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата должна быть в формате yyyy-MM-dd")
    private String purchaseDate;

    @NotNull(message = "ID типа покупки не может быть пустым")
    @Positive(message = "ID типа покупки должен быть положительным числом")
    private Long purchaseTypeId;

    @Min(value = 1, message = "Количество должно быть не менее 1")
    private int count;
}