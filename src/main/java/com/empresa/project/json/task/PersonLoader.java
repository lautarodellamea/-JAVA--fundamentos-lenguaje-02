package com.empresa.project.json.task;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Lee "people-hobbies.json" (carpeta raíz del proyecto) y muestra cada persona.
 */
public class PersonLoader {

    public static void main(String[] args) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("people-hobbies.json")) {
            // El JSON es un array de objetos: necesitamos TypeToken para que Gson
            // sepa que debe construir una List<Person> (por el type erasure).
            Type listType = new TypeToken<List<Person>>() {}.getType();
            List<Person> people = gson.fromJson(reader, listType);

            for (Person person : people) {
                // Si la clave "email" no estaba en el JSON, el campo llega como null.
                String email = person.email() == null ? "no email" : person.email();
                // El array JSON "hobbies" vuelve como List<String>.
                System.out.println(person.name() + " | " + person.age() + " | student=" + person.student()
                        + " | " + email + " | hobbies=" + person.hobbies());
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer people-hobbies.json: " + e.getMessage());
        }
    }
}
