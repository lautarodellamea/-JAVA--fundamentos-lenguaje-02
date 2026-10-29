# mvc-task-app

## Objective
Port the instructor's console task manager (repo `DevTalles-corp/java-mvc-project-final`) into `com.empresa.project.mvc` following the MVC pattern, persisting tasks to JSON with Gson.

## Scope
- Package `com.empresa.project.mvc` with sub-packages `task.model`, `task.view`, `task.controller`, `task.persistence`, `task.exception`, plus `Main`.
- Classes: Task, TaskRepository, TaskPersistence, TaskController, TaskView, TaskException, TaskValidationException, Main.
- Menu: 1 add, 2 remove, 3 update, 4 show all, 5 update status, 6 show completed, 7 show pending, 8 exit.
- Data file `tasks.json` at the project root (relative path), versioned with two sample tasks.
- README: add a section 9 guide (where to start, MVC flow).
- Out of scope: new dependencies, tests framework (JUnit would need user approval), Lombok (section 10).

## Constraints
- Spanish comments (beginner friendly), English identifiers (including `exception` package instead of `excepciones`).
- No pom.xml changes. Gson 2.15.0, Java 27.
- Stay faithful to the instructor's design; fix only real bugs found in the review:
  - `loadTasks()` returns null on an empty `tasks.json` (NPE).
  - Misleading messages: remove says "task cannot be null" when id is not found; update says "invalid index".
  - Success message printed twice (controller and view).
  - Null-safety on `completed` and `findById`.
- Known design leak kept on purpose (and explained in a comment): the controller prints, not only the view.
- TDD: session config strict, but no test framework/runner in the project; verification = Maven compile + running the console app with piped stdin.

## Tasks
- [x] T1 model + exceptions + persistence (Task, TaskRepository, TaskException, TaskValidationException, TaskPersistence).
- [x] T2 controller + view + Main.
- [x] T3 tasks.json sample, README section 9, package-info update for mvc.
- [x] T4 Verify: `mvn -q compile` and run the app with piped input covering add, show, update, status, filters, remove, exit.

## Route
Delegated direct (writer): trigger = writer rule, 2+ non-trivial files.

## Progress / evidence
- Writer delegated (route unchanged). All classes ported under `com.empresa.project.mvc`; Task uses primitive boolean + no-arg constructor.
- `mvn -q compile`: OK (Maven 3.9.16, no output).
- App run with piped stdin (4,1,4,5,6,7,3,2,2 missing id,9,8): add/show/status/filters/update/remove OK; missing id prints "Error: No se encontró una tarea con id 99"; invalid option handled; each success message printed once.
- Empty tasks.json: no crash ("No hay tareas registradas"); missing file: no crash, add creates tasks.json.
- Input ending without option 8 (menu and mid-prompt): clean stop, no stack trace.
- `com.empresa.project.json.task.PersonLoader`: still runs fine.
- tasks.json restored to the two sample tasks. Note: console output shows mojibake on Windows unless `-Dstdout.encoding=UTF-8` is used (display only).

## Next step
Review and open PR / decide JUnit tests (needs dependency approval) / start section 10.
