package com.tfg.backend.config;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@Service
public class ConfigService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File configFile = new File("config/config.json");

    public AppConfig loadConfig() {
        try {
            if (!configFile.exists()) {
                AppConfig defaultConfig = new AppConfig();
                defaultConfig.setLibraryPath("");
                saveConfig(defaultConfig);
                return defaultConfig;
            }

            return objectMapper.readValue(configFile, AppConfig.class);

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo config", e);
        }
    }

    public void saveConfig(AppConfig config) {
        try {
            configFile.getParentFile().mkdirs(); // crea carpeta si no existe
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(configFile, config);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando config", e);
        }
    }
}