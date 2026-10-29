package com.empresa.project.mvc;

import com.empresa.project.mvc.task.controller.TaskController;
import com.empresa.project.mvc.task.model.TaskRepository;
import com.empresa.project.mvc.task.view.TaskView;

/**
 * Punto de entrada de la aplicación de tareas (sección 9).
 *
 * <p>Aquí solo se "arman las piezas" de MVC, de abajo hacia arriba, porque cada
 * capa necesita a la anterior: el repositorio (modelo) no depende de nadie, el
 * controlador recibe el repositorio y la vista recibe el controlador. Esta
 * clase no tiene lógica de negocio.</p>
 *
 * <p>Ejecutar desde la raíz del proyecto: {@code tasks.json} se busca con ruta relativa.</p>
 */
public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
        TaskView view = new TaskView(controller);

        view.showMenu();
    }
}
