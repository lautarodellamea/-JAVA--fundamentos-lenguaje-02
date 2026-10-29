package com.empresa.project.mvc.task.exception;

/**
 * Error de negocio: la operación pedida no se puede hacer con los datos que hay
 * (por ejemplo, una tarea que no existe o una lista vacía).
 *
 * <p>Es una excepción verificada (checked) para obligar a las capas superiores a
 * decidir qué hacer; la vista la atrapa y muestra el mensaje al usuario.</p>
 */
public class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }
}
