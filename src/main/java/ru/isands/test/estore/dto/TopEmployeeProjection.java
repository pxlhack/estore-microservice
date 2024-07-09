package ru.isands.test.estore.dto;

public interface TopEmployeeProjection {
    Long getEmployeeId();
    String getLastname();
    String getFirstname();
    Integer getSalesCount();
}