package com.empresa.project.json.files;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Escribir y leer una LISTA de objetos en un archivo JSON.
 *
 * El archivo "people.json" se crea en la carpeta raíz del proyecto
 * (la carpeta desde donde se ejecuta el programa).
 */
public class JsonWriterReaderListExample {

    private static final String FILE_NAME = "people.json";

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // 1) ESCRIBIR: armamos una lista y la guardamos como un array JSON: [ {...}, {...} ]
        List<Person> people = new ArrayList<>();
        people.add(new Person("Juan", 25, true, "juan@example.com", List.of("soccer")));
        people.add(new Person("Ana", 31, false, null, List.of("music", "books")));

        // try-with-resources cierra el writer solo, aunque ocurra un error.
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            // Al escribir no hace falta TypeToken: Gson mira los objetos reales de la lista.
            gson.toJson(people, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 2) LEER: recuperamos el archivo como una lista de Person.
        try (FileReader reader = new FileReader(FILE_NAME)) {
            // Por el "type erasure", en runtime Java se olvida de que la lista era
            // List<Person>. TypeToken guarda ese tipo para que Gson sepa qué construir.
            // Las llaves {} finales crean una subclase anónima: ese es el truco.
            Type listType = new TypeToken<List<Person>>() {}.getType();

            // El resultado es una List<Person>, no una sola Person.
            List<Person> loadedPeople = gson.fromJson(reader, listType);

            for (Person person : loadedPeople) {
                System.out.println(person);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
