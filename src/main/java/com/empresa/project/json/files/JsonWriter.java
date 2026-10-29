package com.empresa.project.json.files;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Escribir y leer UN solo objeto en un archivo JSON.
 *
 * El archivo "person.json" se crea en la carpeta raíz del proyecto
 * (la carpeta desde donde se ejecuta el programa), porque la ruta es relativa.
 */
public class JsonWriter {

    private static final String FILE_NAME = "person.json";

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Person person = new Person("Carlos", 28, false, "carlos@example.com", List.of("cycling", "cooking"));

        // 1) ESCRIBIR: toJson(objeto, writer) manda el JSON directo al archivo,
        // sin pasar por un String intermedio. try-with-resources cierra el writer solo.
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(person, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 2) LEER: fromJson(reader, clase) lee el archivo y arma el objeto.
        // Como es un solo objeto (no una lista) alcanza con Person.class;
        // TypeToken solo hace falta para tipos genéricos como List<Person>.
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Person loaded = gson.fromJson(reader, Person.class);
            System.out.println(loaded);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
