package com.empresa.project.json.base;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Primer ejemplo con Gson: convertir un objeto Java a JSON y volver.
 *
 * - Serializar:   objeto Java -> texto JSON  (gson.toJson)
 * - Deserializar: texto JSON  -> objeto Java (gson.fromJson)
 */
public class BasicExample {

    public static void main(String[] args) {
        // Gson es la clase principal de la librería. La construimos con GsonBuilder
        // para poder configurarla; setPrettyPrinting() hace que el JSON salga
        // indentado y fácil de leer (sin esto, todo queda en una sola línea).
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Serializar: tomamos un objeto Java y lo convertimos en un String JSON.
        Person person = new Person("Lautaro", 30, true, "lautaro@example.com", List.of("chess", "hiking"));
        String json = gson.toJson(person);
        // Imprime algo así:
        // {
        //   "name": "Lautaro",
        //   "age": 30,
        //   "student": true,
        //   "email": "lautaro@example.com",
        //   "hobbies": [
        //     "chess",
        //     "hiking"
        //   ]
        // }
        System.out.println(json);

        // Deserializar: tomamos un String JSON y lo convertimos de nuevo en un objeto Java.
        // Gson necesita la clase destino (Person.class) para saber qué objeto construir.
        Person fromJson = gson.fromJson(json, Person.class);
        // Imprime: Person[name=Lautaro, age=30, student=true, email=lautaro@example.com, hobbies=[chess, hiking]]
        System.out.println(fromJson);
    }
}
