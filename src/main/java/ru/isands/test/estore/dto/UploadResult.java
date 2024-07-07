package ru.isands.test.estore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UploadResult {
    private final boolean success;
    private final String message;
    private List<String> processedFiles;
    private List<String> failedFiles;
}