package com.empresa.project.mvc.task.controller;

import com.empresa.project.mvc.task.exception.TaskException;
import com.empresa.project.mvc.task.exception.TaskValidationException;
import com.empresa.project.mvc.task.model.Task;
import com.empresa.project.mvc.task.model.TaskRepository;

import java.util.List;

/**
 * CONTROLADOR (la "C" de MVC): intermediario entre la vista y el modelo.
 *
 * <p>Recibe lo que escribió el usuario, valida que no sea nulo ni vacío y, si
 * está bien, le pide el trabajo al {@link TaskRepository}. Si algo está mal,
 * lanza una excepción y la vista decide cómo mostrarla.</p>
 *
 * <p>Los mensajes de éxito ("Tarea agregada...") los imprime solo la vista,
 * para que cada uno salga una única vez.</p>
 */
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, boolean completed)
            throws TaskValidationException, TaskException {
        validateTaskData(id, title, description);
        taskRepository.save(new Task(id, title, description, completed));
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        validateId(id);
        taskRepository.remove(id);
    }

    public void updateTask(String id, String title, String description, boolean completed)
            throws TaskValidationException, TaskException {
        validateTaskData(id, title, description);
        taskRepository.updateTask(new Task(id, title, description, completed));
    }

    public void updateTaskCompleted(String id, boolean completed)
            throws TaskValidationException, TaskException {
        validateId(id);
        taskRepository.updateTaskCompleted(id, completed);
    }

    // NOTA: en MVC estricto solo la vista imprimiría; el controlador devolvería
    // las listas. Mantenemos el diseño del instructor (el controlador imprime)
    // como un compromiso conocido, para mantenernos fieles al curso.
    public void showTasks() throws TaskException {
        printTasks(taskRepository.findAll());
    }

    public void showCompletedTasks() throws TaskException {
        printTasks(taskRepository.findCompletedTasks());
    }

    public void showPendingTasks() throws TaskException {
        printTasks(taskRepository.findPendingTasks());
    }

    private void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void validateId(String id) throws TaskValidationException {
        if (id == null || id.isBlank()) {
            throw new TaskValidationException("El id no puede estar vacío");
        }
    }

    private void validateTaskData(String id, String title, String description)
            throws TaskValidationException {
        validateId(id);
        if (title == null || title.isBlank()) {
            throw new TaskValidationException("El título no puede estar vacío");
        }
        if (description == null || description.isBlank()) {
            throw new TaskValidationException("La descripción no puede estar vacía");
        }
    }
}
