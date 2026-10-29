# Curso Java: JSON con Gson

Proyecto de práctica del curso, organizado por sección: la **sección 8** (Manejo de JSON, ejemplos que se ejecutan uno por uno) y la **sección 9** (aplicación de tareas con MVC).

## Por dónde empezar

1. Abrí el proyecto en IntelliJ y esperá a que Maven descargue Gson (versión 2.15.0, ya declarada en `pom.xml`).
2. Abrí `json/base/BasicExample.java` y ejecutalo con el botón verde de `main`.
3. Seguí el orden de la tabla de abajo. Cada clase tiene comentarios que explican el porqué de cada paso.

> Ejecutá siempre desde la **raíz del proyecto**: los ejemplos leen y escriben archivos con rutas relativas (`person.json`, `config.json`, etc.).

## Orden recomendado

Todas las clases están en `src/main/java/com/empresa/project/json/`.

| Paso | Clase | Qué aprendés |
|------|-------|--------------|
| 1 | `base/BasicExample` | Serializar (objeto → JSON) y deserializar (JSON → objeto) |
| 2 | `files/JsonWriter` | Escribir y leer **un** objeto en un archivo |
| 3 | `files/JsonWriterReaderListExample` | Escribir y leer una **lista** con `TypeToken` |
| 4 | `nested/ConfigLoader` y `nested/ConfigSaver` | Clases anidadas: se convierten en objetos JSON anidados |
| 5 | `task/PersonSaver` y `task/PersonLoader` | Listas de objetos con arrays (`hobbies`) y campos `null` |
| 6 | `json/schema/JsonSchemaValidatorExample` | Validar JSON con Json Schema |

`Person` (en `json/`) es el modelo compartido por varios ejemplos. `AppConfig` es el modelo del paso 4.

## Archivos JSON

| Archivo | Quién lo usa | En git |
|---------|--------------|--------|
| `config.json` | `nested/*` | Sí, es dato de entrada |
| `person-schema.json` | `schema/*` | Sí, es dato de entrada |
| `tasks.json` | `mvc/*` | Sí, es un ejemplo versionado; la app lo reescribe mientras la usás |
| `person.json`, `people.json`, `people-hobbies.json` | `files/*` y `task/*` | No, se generan al ejecutar |

## Estructura del proyecto

```
com.empresa.project
├── json/      sección 8: JSON con Gson (rama 01-manejo-de-json)
├── mvc/       sección 9: gestor de tareas con MVC (rama 02-mvc)
└── lombok/    sección 10: bonus Lombok (todavía vacío)
```

Cada sección va en su propio paquete y, cuando se trabaja, en su propia rama (`01-manejo-de-json`, y las siguientes).

## Sección 9: MVC (rama 02-mvc)

Gestor de tareas por consola. Ejecutá `com.empresa.project.mvc.Main` desde IntelliJ, siempre desde la **raíz del proyecto** (ahí vive `tasks.json`).

| Rol | Clase | Qué hace |
|-----|-------|----------|
| Modelo | `Task` + `TaskRepository` | Los datos y su lista en memoria |
| Vista | `TaskView` | Menú y lectura de la consola |
| Controlador | `TaskController` | Valida la entrada y delega |
| Persistencia | `TaskPersistence` | Lee y escribe `tasks.json` con Gson |

Menú: `1` agregar, `2` eliminar, `3` actualizar, `4` mostrar todas, `5` cambiar estado, `6` completadas, `7` pendientes, `8` salir.

Flujo: `TaskView` -> `TaskController` -> `TaskRepository` -> `TaskPersistence`.

## Nota sobre Json Schema

El paso 6 usa la dependencia `com.networknt:json-schema-validator` (soporta draft 2020-12). El instructor usa Everit, que solo llega hasta draft 7.
