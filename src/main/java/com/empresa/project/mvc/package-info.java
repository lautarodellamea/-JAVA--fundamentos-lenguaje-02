/**
 * Sección 9: aplicación de consola para gestionar tareas con el patrón MVC.
 *
 * <p>Punto de entrada: {@link com.empresa.project.mvc.Main}. Las tareas se guardan
 * en {@code tasks.json} (raíz del proyecto) usando Gson.</p>
 *
 * <ul>
 *   <li>{@code task.model}: modelo ({@code Task}, {@code TaskRepository}).</li>
 *   <li>{@code task.view}: vista de consola ({@code TaskView}).</li>
 *   <li>{@code task.controller}: controlador ({@code TaskController}).</li>
 *   <li>{@code task.persistence}: lectura y escritura del JSON ({@code TaskPersistence}).</li>
 *   <li>{@code task.exception}: errores de validación y de negocio.</li>
 * </ul>
 *
 * <p>Flujo: vista, controlador, repositorio, persistencia.</p>
 */
package com.empresa.project.mvc;
