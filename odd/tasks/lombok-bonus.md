# lombok-bonus

## Objective
Port the instructor's Lombok bonus (repo `DevTalles-corp/java-lombok`) into `com.empresa.project.lombok`: a `Person` class using `@Builder` and `@ToString`, and a `Main` that builds and prints one.

## Scope
- `lombok/Person.java` (`@Builder`, `@ToString`; fields name, age, lastName) and `lombok/Main.java`.
- pom.xml: add `org.projectlombok:lombok:1.18.48` (scope provided) and configure `maven-compiler-plugin` with `annotationProcessorPaths` for Lombok, because since JDK 23 javac no longer runs annotation processors found on the classpath implicitly.
- README: add a section 10 guide.
- Out of scope: other dependencies, JUnit, refactoring the mvc package to Lombok.

## Constraints
- User approved exactly: Lombok 1.18.48 provided + compiler configuration. Nothing else in pom.xml.
- Spanish comments (beginner friendly), English identifiers.
- Java 27. Instructor used Lombok 1.18.36 (old); we use the latest verified on Maven Central (2026-09-01).
- Must not break sections 8 and 9 (compile + run one example of each).
- TDD: session config strict, but no test framework/runner; verification = Maven compile + run.

## Tasks
- [x] T1 pom.xml: Lombok dependency + compiler plugin annotation processor path.
- [x] T2 `lombok/Person` and `lombok/Main` with explanatory comments, `package-info` update.
- [x] T3 README section 10.
- [x] T4 Verify: `mvn -q compile`, run lombok Main, re-run a json example and the mvc app.

## Route
Delegated direct (writer): trigger = writer rule (pom + 2 classes + README).

## Progress / evidence
- Maven 3.9.16 (temporary, outside project), Java 27: `mvn -q clean compile` succeeded with Lombok 1.18.48 (no compile errors; the `lombok` package name does not clash with `import lombok.Builder`).
- Compiler printed only JVM warnings from Lombok: `WARNING: A terminally deprecated method in sun.misc.Unsafe has been called` / `sun.misc.Unsafe::objectFieldOffset has been called by lombok.permit.Permit` / `... will be removed in a future release`.
- `lombok.Main` output: `Person(name=Gabriel, age=22, lastName=Chaldú)` and `Person(name=Ana, age=30, lastName=null)`.
- `javap -p` on Person shows `builder()`, `toString()` and the all-args constructor generated.
- No regressions: `PersonLoader`, `JsonSchemaValidatorExample` and `mvc.Main` (stdin `4`,`8`) run as before; `tasks.json` unchanged in git status.

## Next step
Review / open PRs / decide JUnit tests (needs dependency approval).
