package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.dto.UploadResult;
import ru.isands.test.estore.service.FileProcessingService;

import java.io.IOException;
import java.util.Objects;

@RestController
@Tag(name = "FileUpload", description = "Сервис для загрузки файлов")
@RequestMapping("/estore/api/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileProcessingService fileProcessingService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка zip-файла")
    public ResponseEntity<UploadResult> uploadZipFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new UploadResult(false, "Пожалуйста, выберите файл для загрузки."));
        }

        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".zip")) {
            return ResponseEntity.badRequest().body(new UploadResult(false, "Пожалуйста, загрузите ZIP-файл."));
        }

        try {
            UploadResult result = fileProcessingService.processZipFile(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UploadResult(false, "Ошибка при обработке файла: " + e.getMessage()));
        }
    }
}