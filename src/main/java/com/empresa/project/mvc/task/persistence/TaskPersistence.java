package com.empresa.project.mvc.task.persistence;

import com.empresa.project.mvc.task.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * PERSISTENCIA: la única clase que conoce el archivo {@code tasks.json}.
 *
 * <p>No es parte de MVC en sentido estricto, pero aislar el acceso a disco aquí
 * permite cambiar el formato o el lugar de guardado sin tocar el resto. Usa Gson
 * para pasar de lista de tareas a JSON y de JSON a lista de tareas.</p>
 *
 * <p>La ruta es relativa: el archivo se busca en la carpeta desde donde se
 * ejecuta el programa (la raíz del proyecto).</p>
 */
public class TaskPersistence {

    private static final String FILE_PATH = "tasks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private TaskPersistence() {
    }

    /** Escribe toda la lista en el archivo, con formato legible. */
    public static void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Lee la lista del archivo. Devuelve una lista vacía si el archivo no existe,
     * está vacío o no se puede leer, para que el programa nunca arranque con {@code null}.
     */
    public static List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(reader, listType);
            // Gson devuelve null cuando el archivo está vacío.
            return tasks != null ? tasks : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
