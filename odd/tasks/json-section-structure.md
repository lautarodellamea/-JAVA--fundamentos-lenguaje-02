# json-section-structure

## Objective
Organize the project by course section and port the instructor's JSON examples (repo `DevTalles-corp/java-section-9`, which actually holds the JSON section) into `com.empresa.project.json`, split in sub-packages. Leave room for the next sections (MVC, Lombok).

## Scope
- Packages: `json` (section 8, this branch), `mvc` (section 9) and `lombok` (section 10) as empty placeholders via `package-info.java`.
- Sub-packages in `json`: `base`, `files`, `nested`, `task`.
- JSON Schema validation was initially out of scope; added after the user approved the dependency `com.networknt:json-schema-validator:3.0.8`.

## Constraints
- Code/comment language: Spanish comments (user asked), English identifiers.
- Do not add or bump dependencies without user confirmation (only the networknt dependency was approved).
- Gson 2.15.0, Java 27; Maven was not installed at first (a temporary Maven 3.9.16 was used for T5 verification).
- TDD: session config says strict, but the project has no test framework or runner; examples are `main()` demos. Verification = javac compile + run.

## Data files (project root, relative paths)
`config.json` (nested, versioned input). Generated on run and git-ignored: `person.json` (JsonWriter), `people.json` (list example), `people-hobbies.json` (task).

## Tasks
- [x] T1 Move/split existing examples into `base` and `files`, extend `Person` with `email` and `hobbies`.
- [x] T2 Add `nested` (AppConfig, ConfigLoader, ConfigSaver) and `task` (PersonSaver, PersonLoader).
- [x] T3 Add `package-info.java` for `json`, `mvc`, `lombok`.
- [x] T4 Compile with javac against Gson 2.15.0 and run each example.
- [x] T5 Add JSON Schema validation (networknt 3.0.8) with example

## Route
Delegated direct (writer): trigger = writer rule, 2+ non-trivial files.

## Progress / evidence
- Writer report: javac (Gson 2.15.0 jar from Maven Central, UTF-8) compiled all sources; 8 examples ran with exit 0 (BasicExample, JsonWriter, JsonWriterReaderListExample, ConfigLoader, ConfigSaver, ConfigLoader again, PersonSaver, PersonLoader).
- Parent spot check: `task.PersonLoader` re-run, printed Lucia and Marta ("no email" for null), exit 0.
- T5: added `person-schema.json`, `json/schema/JsonSchemaValidatorExample`, README step 6 and the pom dependency. Verified with Apache Maven 3.9.16 (temporary, outside the project): `mvn -q compile` exit 0, `dependency:build-classpath` exit 0, the schema example ran with exit 0 (valid JSON accepted; 4 errors reported for the invalid one; Gson output with null email valid) and `task.PersonLoader` re-ran fine. Only an expected SLF4J "no providers" warning appeared.
- Note: format `email` is annotation-only by default in draft 2020-12, so it is not enforced.
- Review tier: not assessed (`gentle-ai review` not run).

## Next step
Open PR / start section 9 branch
