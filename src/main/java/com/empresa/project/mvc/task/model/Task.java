package com.empresa.project.mvc.task.model;

import java.util.Objects;

/**
 * MODELO (la "M" de MVC): representa una tarea y nada más.
 *
 * <p>No sabe de consola ni de archivos; solo guarda datos. Gson convierte esta
 * clase a JSON y viceversa usando sus campos. Por eso tiene un constructor sin
 * argumentos: así Gson puede crear el objeto sin recurrir a trucos internos
 * (Unsafe). El estado {@code completed} es un {@code boolean} primitivo, por lo
 * que nunca puede ser {@code null} dentro del modelo.</p>
 *
 * <p>Dos tareas son iguales si tienen el mismo {@code id}.</p>
 */
public class Task {

    private String id;
    private String title;
    private String description;
    private boolean completed;

    /** Constructor vacío, necesario para que Gson cree la tarea al leer el JSON. */
    public Task() {
    }

    public Task(String id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
