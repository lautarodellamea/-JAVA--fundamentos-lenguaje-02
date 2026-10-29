package com.empresa.project.json.nested;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

/**
 * Lee "config.json" y lo convierte en un AppConfig.
 *
 * La ruta es relativa: el archivo se busca en la carpeta raíz del proyecto
 * (la carpeta desde donde se ejecuta el programa).
 */
public class ConfigLoader {

    public static void main(String[] args) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("config.json")) {
            // El objeto anidado "settings" del JSON se rellena solo en AppConfig.settings.
            AppConfig config = gson.fromJson(reader, AppConfig.class);
            System.out.println(config);
            System.out.println("Tema: " + config.settings.theme);
        } catch (IOException e) {
            System.out.println("No se pudo leer config.json: " + e.getMessage());
        }
    }
}
