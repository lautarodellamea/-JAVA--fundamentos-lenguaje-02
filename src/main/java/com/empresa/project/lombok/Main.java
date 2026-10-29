package com.empresa.project.lombok;

/**
 * Ejemplo de la sección 10: crea objetos {@link Person} con el builder de Lombok y los imprime.
 */
public class Main {

    public static void main(String[] args) {
        // Con el builder indicamos cada campo por su nombre; el orden no importa y se lee fácil.
        Person person = Person.builder()
                .name("Gabriel")
                .age(22)
                .lastName("Chaldú")
                .build();

        // @ToString permite imprimir el objeto con todos sus campos.
        System.out.println(person);

        // Si no indicamos un campo, queda con su valor por defecto:
        // null para los objetos (String) y 0 para los números (int).
        Person incomplete = Person.builder()
                .name("Ana")
                .age(30)
                .build();

        System.out.println(incomplete);
    }
}
