package ru.isands.test.estore.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@Configuration
@PropertySource(value = "classpath:file-processing.properties")
@ConfigurationProperties(prefix = "file-processing")
public class FileProcessingConfig {
    private Map<String, Integer> priorities = new LinkedHashMap<>();
}