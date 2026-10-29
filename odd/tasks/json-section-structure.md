# json-section-structure

## Objective
Organize the project by course section and port the instructor's JSON examples (repo `DevTalles-corp/java-section-9`, which actually holds the JSON section) into `com.empresa.project.json`, split in sub-packages. Leave room for the next sections (MVC, Lombok).

## Scope
- Packages: `json` (section 8, this branch), `mvc` (section 9) and `lombok` (section 10) as empty placeholders via `package-info.java`.
- Sub-packages in `json`: `base`, `files`, `nested`, `task`.
- Out of scope: JSON Schema validation (needs a new dependency; waiting for user decision).

## Constraints
- Code/comment language: Spanish comments (user asked), English identifiers.
- Do not add or bump dependencies without user confirmation.
- Gson 2.15.0, Java 27, no Maven/test runner available in the shell.
- TDD: session config says strict, but the project has no test framework or runner; examples are `main()` demos. Verification = javac compile + run.

## Data files (project root, relative paths)
`config.json` (nested, versioned input). Generated on run and git-ignored: `person.json` (JsonWriter), `people.json` (list example), `people-hobbies.json` (task).

## Tasks
- [x] T1 Move/split existing examples into `base` and `files`, extend `Person` with `email` and `hobbies`.
- [x] T2 Add `nested` (AppConfig, ConfigLoader, ConfigSaver) and `task` (PersonSaver, PersonLoader).
- [x] T3 Add `package-info.java` for `json`, `mvc`, `lombok`.
- [x] T4 Compile with javac against Gson 2.15.0 and run each example.

## Route
Delegated direct (writer): trigger = writer rule, 2+ non-trivial files.

## Progress / evidence
- Writer report: javac (Gson 2.15.0 jar from Maven Central, UTF-8) compiled all sources; 8 examples ran with exit 0 (BasicExample, JsonWriter, JsonWriterReaderListExample, ConfigLoader, ConfigSaver, ConfigLoader again, PersonSaver, PersonLoader).
- Parent spot check: `task.PersonLoader` re-run, printed Lucia and Marta ("no email" for null), exit 0.
- Not verified: Maven build (mvn not installed here).
- Review tier: not assessed (`gentle-ai review` not run).

## Next step
User decision on JSON Schema dependency (T5, not started).
