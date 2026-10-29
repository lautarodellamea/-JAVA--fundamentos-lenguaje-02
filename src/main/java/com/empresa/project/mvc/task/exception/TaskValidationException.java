package com.empresa.project.mvc.task.exception;

/**
 * Error de validación: los datos que escribió el usuario son inválidos
 * (por ejemplo, un id vacío).
 *
 * <p>Se separa de {@link TaskException} para distinguir "el dato está mal"
 * de "la operación no se pudo completar". La lanza el controlador.</p>
 */
public class TaskValidationException extends Exception {

    public TaskValidationException(String message) {
        super(message);
    }
}
