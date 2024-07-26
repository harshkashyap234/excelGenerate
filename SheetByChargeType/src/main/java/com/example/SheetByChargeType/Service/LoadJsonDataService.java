package com.example.SheetByChargeType.Service;
import com.example.SheetByChargeType.Model.ExcelConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class LoadJsonDataService {
    private final ObjectMapper objectMapper;
    public LoadJsonDataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<ExcelConfig> readJsonFile(String sheetName) {
        try {
            ClassPathResource resource = new ClassPathResource(sheetName + ".json");
            return Optional.of(objectMapper.readValue(resource.getInputStream(), ExcelConfig.class));
        } catch (Exception e) {
            log.error("Unable to load config for bulk order due to {}", e.getMessage());
            return Optional.empty();
        }
    }

}

