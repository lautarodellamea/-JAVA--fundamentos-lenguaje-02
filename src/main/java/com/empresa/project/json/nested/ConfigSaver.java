package com.empresa.project.json.nested;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Carga "config.json", cambia el tema a "Dark" y lo guarda de nuevo.
 *
 * Si el archivo no existe, primero lo crea con valores por defecto para que
 * el ejemplo funcione solo. La ruta es relativa a la carpeta raíz del proyecto.
 */
public class ConfigSaver {

    private static final String FILE_NAME = "config.json";

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Si no existe el archivo, lo creamos con valores por defecto.
            if (!new File(FILE_NAME).exists()) {
                try (FileWriter writer = new FileWriter(FILE_NAME)) {
                    gson.toJson(defaultConfig(), writer);
                }
                System.out.println("config.json no existía: se creó con valores por defecto.");
            }

            // 1) Leer la configuración actual.
            AppConfig config;
            try (FileReader reader = new FileReader(FILE_NAME)) {
                config = gson.fromJson(reader, AppConfig.class);
            }

            // 2) Modificar solo un valor del objeto anidado.
            config.settings.theme = "Dark";

            // 3) Escribir de nuevo. FileWriter reemplaza el contenido anterior del archivo.
            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                gson.toJson(config, writer);
            }
            System.out.println("Guardado: " + config);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static AppConfig defaultConfig() {
        AppConfig config = new AppConfig();
        config.appName = "Demo App";
        config.version = "1.0";
        config.settings = new AppConfig.Settings();
        config.settings.theme = "Light";
        config.settings.notifications = true;
        config.settings.language = "es";
        return config;
    }
}
