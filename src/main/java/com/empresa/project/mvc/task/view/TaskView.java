package com.empresa.project.mvc.task.view;

import com.empresa.project.mvc.task.controller.TaskController;
import com.empresa.project.mvc.task.exception.TaskException;
import com.empresa.project.mvc.task.exception.TaskValidationException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * VISTA (la "V" de MVC): lo único que el usuario ve y toca.
 *
 * <p>Muestra el menú, lee lo que se escribe en la consola y llama al
 * {@link TaskController}. No guarda tareas ni decide reglas: si algo falla,
 * solo atrapa la excepción y muestra el mensaje como "Error: ...".</p>
 *
 * <p>Si la entrada se termina (por ejemplo, Ctrl+D o una entrada redirigida),
 * el programa se detiene sin mostrar errores técnicos.</p>
 */
public class TaskView {

    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        try {
            while (scanner.hasNextLine()) {
                printMenu();
                String option = scanner.nextLine().trim();
                if (option.equals("8")) {
                    System.out.println("Saliendo del sistema");
                    return;
                }
                handleOption(option);
            }
        } catch (NoSuchElementException e) {
            // La entrada terminó en medio de una pregunta: salimos en silencio.
        }
        System.out.println("\nFin de la entrada. Saliendo del sistema");
    }

    private void printMenu() {
        System.out.println("\nGestión de Tareas");
        System.out.println("1. Agregar Tarea");
        System.out.println("2. Eliminar Tarea");
        System.out.println("3. Actualizar Tarea");
        System.out.println("4. Mostrar Tareas");
        System.out.println("5. Actualizar estado de la tarea");
        System.out.println("6. Mostrar Tareas completadas");
        System.out.println("7. Mostrar Tareas pendientes");
        System.out.println("8. Salir");
        System.out.println("Seleccione una opción");
    }

    private void handleOption(String option) {
        try {
            switch (option) {
                case "1" -> addTaskView();
                case "2" -> removeTaskView();
                case "3" -> updateTaskView();
                case "4" -> {
                    System.out.println("\nLa lista de Tareas");
                    taskController.showTasks();
                }
                case "5" -> updateTaskCompletedView();
                case "6" -> {
                    System.out.println("Tareas Completadas");
                    taskController.showCompletedTasks();
                }
                case "7" -> {
                    System.out.println("Tareas Pendientes");
                    taskController.showPendingTasks();
                }
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addTaskView() throws TaskValidationException, TaskException {
        String id = ask("Ingresar ID");
        String title = ask("Ingrese el Título");
        String description = ask("Ingresar la descripción");
        boolean completed = askBoolean();
        taskController.addTask(id, title, description, completed);
        System.out.println("Tarea agregada correctamente");
    }

    private void removeTaskView() throws TaskValidationException, TaskException {
        String id = ask("Ingrese el Id a eliminar");
        taskController.removeTask(id);
        System.out.println("Tarea eliminada correctamente");
    }

    private void updateTaskView() throws TaskValidationException, TaskException {
        String id = ask("Ingresar ID de la tarea a actualizar");
        String title = ask("Ingrese el Título");
        String description = ask("Ingresar la descripción");
        boolean completed = askBoolean();
        taskController.updateTask(id, title, description, completed);
        System.out.println("Tarea actualizada correctamente");
    }

    private void updateTaskCompletedView() throws TaskValidationException, TaskException {
        String id = ask("Ingrese el ID de la Tarea");
        boolean completed = askBoolean();
        taskController.updateTaskCompleted(id, completed);
        System.out.println("Estado de la tarea actualizado correctamente");
    }

    /** Muestra una pregunta y devuelve la línea escrita (la validación es del controlador). */
    private String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /** Repite la pregunta hasta que el usuario escriba exactamente true o false. */
    private boolean askBoolean() {
        while (true) {
            String input = ask("¿Está completada? true/false").trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            }
            if (input.equals("false")) {
                return false;
            }
            System.out.println("El valor ingresado no es correcto, ingrese: 'true' o 'false'");
        }
    }
}
