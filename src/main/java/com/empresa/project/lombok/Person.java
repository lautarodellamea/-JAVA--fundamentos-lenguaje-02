package com.empresa.project.lombok;

import lombok.Builder;
import lombok.ToString;

/**
 * Persona de ejemplo para la sección 10 (Lombok).
 *
 * <p><b>¿Qué es Lombok?</b> Es una librería que escribe por nosotros el código repetitivo
 * (constructores, getters, toString, etc.). Nosotros solo ponemos anotaciones y Lombok genera
 * ese código cuando se compila el proyecto. En el archivo {@code .java} no lo vemos, pero sí
 * existe en el {@code .class}.
 *
 * <p><b>{@code @Builder}</b> genera un "constructor paso a paso": se crea el objeto indicando
 * cada campo por su nombre ({@code Person.builder().name("Ana").age(30).build()}). Es más legible
 * y más seguro que un constructor con muchos parámetros posicionales, donde es fácil mezclar el
 * orden (por ejemplo, poner el apellido donde iba el nombre).
 *
 * <p><b>{@code @ToString}</b> genera un {@code toString()} que muestra todos los campos, así
 * {@code System.out.println(person)} imprime algo útil en vez de {@code Person@1b2c3d}.
 *
 * <p><b>Comparación con la sección 9:</b> la clase {@code Task} del paquete {@code mvc} se
 * escribió a mano: constructor, getters y {@code toString()}. Aquí con dos anotaciones y tres
 * campos obtenemos algo equivalente, con mucho menos código que mantener.
 *
 * <p>Nota: el paquete de esta clase se llama {@code lombok}, pero {@code import lombok.Builder}
 * apunta a la librería Lombok, no a este paquete.
 */
@Builder
@ToString
public class Person {

    private String name;
    private int age;
    private String lastName;
}
