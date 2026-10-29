package com.empresa.project.mvc.task.model;

import com.empresa.project.mvc.task.exception.TaskException;
import com.empresa.project.mvc.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * MODELO (la "M" de MVC): guarda las tareas en memoria y aplica las reglas de datos.
 *
 * <p>Al crearse carga la lista desde {@link TaskPersistence}. Después de cada
 * cambio (agregar, borrar, actualizar) vuelve a guardar el archivo, así el JSON
 * siempre refleja lo que hay en memoria. El controlador habla con esta clase y
 * nunca toca el archivo directamente.</p>
 */
public class TaskRepository {

    private final List<Task> tasks;

    public TaskRepository() {
        this.tasks = TaskPersistence.loadTasks();
    }

    /** Agrega una tarea; rechaza ids repetidos. */
    public void save(Task task) throws TaskException {
        if (tasks.contains(task)) {
            throw new TaskException("Ya existe una tarea con id " + task.getId());
        }
        tasks.add(task);
        TaskPersistence.saveTasks(tasks);
    }

    /** Busca por id; devuelve {@code null} si no existe (o si el id es null). */
    public Task findById(String id) {
        if (id == null) {
            return null;
        }
        for (Task task : tasks) {
            if (id.equals(task.getId())) {
                return task;
            }
        }
        return null;
    }

    public List<Task> findAll() throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException("No hay tareas registradas");
        }
        return tasks;
    }

    public List<Task> findCompletedTasks() throws TaskException {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        if (completedTasks.isEmpty()) {
            throw new TaskException("No hay tareas completadas");
        }
        return completedTasks;
    }

    public List<Task> findPendingTasks() throws TaskException {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pendingTasks.add(task);
            }
        }
        if (pendingTasks.isEmpty()) {
            throw new TaskException("No hay tareas pendientes");
        }
        return pendingTasks;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if (task == null) {
            throw new TaskException("No se encontró una tarea con id " + id);
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    /** Reemplaza la tarea que tenga el mismo id. */
    public void updateTask(Task updatedTask) throws TaskException {
        if (updatedTask == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(updatedTask.getId());
        if (index == -1) {
            throw new TaskException("No se encontró una tarea con id " + updatedTask.getId());
        }
        tasks.set(index, updatedTask);
        TaskPersistence.saveTasks(tasks);
    }

    public void updateTaskCompleted(String id, boolean completed) throws TaskException {
        Task task = findById(id);
        if (task == null) {
            throw new TaskException("No se encontró una tarea con id " + id);
        }
        task.setCompleted(completed);
        TaskPersistence.saveTasks(tasks);
    }

    private int findIndexById(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
