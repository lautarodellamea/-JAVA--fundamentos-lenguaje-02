package com.empresa.project.json.task;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Guarda una lista de personas (con hobbies) en "people-hobbies.json".
 *
 * La ruta es relativa: el archivo se crea en la carpeta raíz del proyecto.
 */
public class PersonSaver {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Cada List<String> de hobbies se escribe como un array JSON: ["a", "b"].
        // Marta tiene email null: Gson omite el campo, así que la clave "email"
        // no aparece en el JSON de Marta.
        List<Person> people = List.of(
                new Person("Lucia", 22, true, "lucia@example.com", List.of("painting", "swimming")),
                new Person("Marta", 40, false, null, List.of("gardening")));

        try (FileWriter writer = new FileWriter("people-hobbies.json")) {
            gson.toJson(people, writer);
            System.out.println("Archivo people-hobbies.json guardado.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
