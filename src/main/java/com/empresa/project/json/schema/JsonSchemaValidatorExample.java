package com.empresa.project.json.schema;

import com.empresa.project.json.Person;
import com.google.gson.Gson;
import com.networknt.schema.Error;
import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SpecificationVersion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Validar JSON con Json Schema usando la librería networknt json-schema-validator 3.x.
 *
 * Un "schema" (esquema) es un documento JSON que DESCRIBE cómo debe ser otro JSON:
 * qué claves existen, de qué tipo es cada una, cuáles son obligatorias, qué largo
 * mínimo tiene un texto, etc. Es como un contrato: si el JSON no lo cumple, la
 * validación devuelve una lista de errores que explican qué falló.
 *
 * Validamos el TEXTO JSON y no el objeto Java por una razón simple: el JSON es lo que
 * llega desde afuera (un archivo, una API, otro sistema) y no podemos confiar en él.
 * Un objeto Java ya pasó por Gson y el compilador ya garantiza sus tipos; los datos
 * incorrectos hay que detectarlos ANTES de convertirlos en objetos.
 *
 * Ejecutá desde la raíz del proyecto: el schema se lee con la ruta relativa
 * "person-schema.json".
 */
public class JsonSchemaValidatorExample {

    public static void main(String[] args) throws IOException {
        // Leemos el schema como texto. La librería lo interpreta según su "$schema"
        // (aquí, draft 2020-12), que le indica qué reglas existen.
        String schemaText = Files.readString(Path.of("person-schema.json"));
        SchemaRegistry registry = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_2020_12);
        Schema schema = registry.getSchema(schemaText, InputFormat.JSON);

        // (a) JSON válido: cumple todas las reglas, así que la lista de errores queda vacía.
        String validJson = """
                [
                  {"name": "Lucia", "age": 30, "student": true,
                   "email": "lucia@example.com", "hobbies": ["leer", "correr"]},
                  {"name": "Marta", "age": 22, "student": false}
                ]
                """;
        System.out.println("--- (a) JSON válido ---");
        validate(schema, validJson);

        // (b) JSON inválido, con varios problemas a propósito:
        //  - primera persona: name muy corto, age negativo y student como texto.
        //  - segunda persona: falta el campo obligatorio "age".
        String invalidJson = """
                [
                  {"name": "Al", "age": -5, "student": "yes"},
                  {"name": "Marta", "student": false}
                ]
                """;
        System.out.println("--- (b) JSON inválido ---");
        validate(schema, invalidJson);

        // (c) JSON generado por Gson. El email es null, y Gson omite los campos null,
        // así que la clave "email" no aparece. Sigue siendo válido porque en el schema
        // "email" NO está en "required".
        List<Person> people = List.of(new Person("Pedro", 40, false, null, List.of("cine")));
        String generatedJson = new Gson().toJson(people);
        System.out.println("--- (c) JSON generado con Gson (email null) ---");
        System.out.println(generatedJson);
        validate(schema, generatedJson);
    }

    /**
     * Valida el texto contra el schema e imprime el resultado.
     * La lista vacía significa "sin errores"; cada Error describe una regla incumplida.
     */
    private static void validate(Schema schema, String json) {
        List<Error> errors = schema.validate(json, InputFormat.JSON);
        if (errors.isEmpty()) {
            System.out.println("JSON válido");
            return;
        }
        for (Error error : errors) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
