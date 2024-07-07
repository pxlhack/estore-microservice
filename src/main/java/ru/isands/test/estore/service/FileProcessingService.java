package ru.isands.test.estore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.config.FileProcessingConfig;
import ru.isands.test.estore.dto.UploadResult;
import ru.isands.test.estore.processor.FileProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class FileProcessingService {

    private final FileProcessingConfig config;
    private final List<FileProcessor> fileProcessors;

    public UploadResult processZipFile(MultipartFile file) throws IOException {
        List<String> processedFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();
        Map<String, byte[]> fileContents = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    String fileName = zipEntry.getName();
                    byte[] content = readEntryContent(zis);
                    fileContents.put(fileName, content);
                }
                zis.closeEntry();
            }
        }

        List<String> sortedFiles = new ArrayList<>(fileContents.keySet());
        sortedFiles.sort((a, b) -> {
            int priorityA = getFilePriority(a);
            int priorityB = getFilePriority(b);
            if (priorityA != priorityB) {
                return Integer.compare(priorityA, priorityB);
            }
            return a.compareTo(b);
        });

        for (String fileName : sortedFiles) {
            try {
                boolean success = processFileContent(fileContents.get(fileName), fileName);
                if (success) {
                    processedFiles.add(fileName);
                } else {
                    failedFiles.add(fileName);
                }
            } catch (Exception e) {
                failedFiles.add(fileName);
            }
        }

        boolean overallSuccess = failedFiles.isEmpty();
        String message = overallSuccess ? "Все файлы успешно обработаны" : "Некоторые файлы не удалось обработать";
        return new UploadResult(overallSuccess, message, processedFiles, failedFiles);
    }

    private int getFilePriority(String fileName) {
        for (Map.Entry<String, Integer> entry : config.getPriorities().entrySet()) {
            if (fileName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    private byte[] readEntryContent(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = zis.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        return outputStream.toByteArray();
    }

    private boolean processFileContent(byte[] content, String fileName) {
        for (FileProcessor processor : fileProcessors) {
            if (processor.canProcess(fileName)) {
                return processor.process(content, fileName);
            }
        }
        System.out.println("Не найден подходящий обработчик для файла: " + fileName);
        return false;
    }
}