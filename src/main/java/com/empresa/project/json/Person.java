package com.empresa.project.json;

import java.util.List;

/**
 * Clase de datos simple para practicar JSON con Gson.
 *
 * Un record es una forma compacta de declarar una clase que solo guarda datos.
 * Java genera por nosotros el constructor, los getters (name(), age(), student(),
 * email(), hobbies()), equals, hashCode y toString.
 *
 * Gson lee los campos de esta clase para armar el JSON, así que cada campo
 * se convierte en una clave:
 * {"name": "...", "age": 30, "student": true, "email": "...", "hobbies": ["a", "b"]}
 *
 * - email puede ser null. Cuando un campo es null, Gson lo OMITE del JSON
 *   (la clave ni aparece). Al leer ese JSON de vuelta, el campo queda en null.
 * - hobbies es una List<String>, que en JSON se convierte en un array: ["a", "b"].
 *
 * Esta clase vive en el paquete json porque la comparten los subpaquetes
 * (base, files, task).
 */
public record Person(String name, int age, boolean student, String email, List<String> hobbies) {
}
