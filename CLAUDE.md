# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A student exercise template for an OAMK Java course topic
("3-collections"), covering the Java Collections Framework (`HashSet`,
`HashMap`, `ArrayList`, `Vector`). `points.json` maps each test class to a
point value, used by the CI autograder — it is not meant to be edited by
students.

`assignments_3.txt` contains the authoritative spec for this topic's four
exercises:

- **SetOperations (1p)** — private `HashSet<Integer> set1, set2`;
  constructor `SetOperations(HashSet<Integer> s1, HashSet<Integer> s2)`;
  `union()`, `intersection()`, `difference()`, each returning
  `HashSet<Integer>`.
- **CharacterCounter (2p)** — single method
  `count(String inputString): Map<Character, Integer>` counting character
  occurrences (empty string → empty map).
- **DataOrganizer (2p)** — single method
  `groupAndSort(ArrayList<Integer> numbers): HashMap<String, ArrayList<Integer>>`
  splitting the input into key `"Even"` (ascending) and key `"Odd"`
  (descending) lists.
- **Fibonacci (3p)** — private `Vector<Integer>` sequence storage;
  constructor `Fibonacci(Integer n)` pre-populating the first `n` numbers;
  `getNumbers()` getter; `addNext()` (returns the next number, appending
  it); an overload `addNext(Integer)` (extends the sequence up to that
  index); `isFibonacci(Integer)`; `compare(Vector<Integer>)`. Note this
  topic's `Fibonacci` is a different class from `1-javabasics-template`'s
  `Fibonacci` (different package instance, richer API) — don't confuse the
  two when referencing "the Fibonacci exercise".

`SetOperations` and `Fibonacci` are full-class-design exercises (empty stub,
students design fields/constructor/methods themselves — see the recipe
below), so `mvn test` fails with a *compile* error on a fresh clone until
they're implemented; `CharacterCounter` and `DataOrganizer` are
single-method exercises (signature predefined, body throws
`UnsupportedOperationException` until filled in).

## Commands

- Run all tests: `mvn test`
- Run a single test class: `mvn test -Dtest=FibonacciTest`
- Run a single test method: `mvn test -Dtest=FibonacciTest#addNextAppendsAndReturnsTheNextNumber`
- Compile only: `mvn compile`

Requires JDK 22 (`maven.compiler.release` in `pom.xml`). No linter is
configured.

## Architecture / conventions

- All exercise code lives under the single `exercises` package
  (`src/main/java/exercises`), mirrored 1:1 by test classes in
  `src/test/java/exercises`.
- `Main.java` is a placeholder entry point only, unrelated to the exercises
  themselves — it exists so the IDE's "Run" button works immediately.
- Test files are the source of truth for expected behavior and are restored
  from the canonical upstream template by CI before grading (see below) — do
  not rely on modifying them to make an exercise "pass".

## CI autograding (`.github/workflows/classroom-ci.yml`)

On every push, CI clones the canonical `OAMK-Java-2026/3-collections-template`
repo, overwrites the local `src/test` and `points.json` with the canonical
versions, runs `mvn test`, then computes a partial score per test class
(`points * passed/total`, rounded) and posts it as a commit status. This
means:

- Local edits to `src/test/**` or `points.json` have no effect on the graded
  score — only `src/main/java/exercises/**` (or new files there) matter.
- A missing Surefire report for a class (e.g. from a compile error) scores
  that class as 0.

## Reusable recipe: building/updating an exercise-topic repo

This repo is one of a family of sibling OAMK Java course template repos
(`0-helloworld-template`, `1-javabasics-template`, `2-javaoop-template`,
`3-collections-template`, ...), all sharing the same skeleton (`pom.xml`,
`src/main/java/exercises`, `src/test/java/exercises`, `points.json`,
`.vscode`, `.github/workflows/classroom-ci.yml`). When asked to turn a
topic's `assignments_N.txt` (plus any UML/reference images committed
alongside it) into exercise stubs in one of these repos, follow this
recipe:

1. **One class per exercise, named after the concept** (e.g. `Greeting`,
   `Validator`, `Apartment`, `Vehicle`, `Garage`) — not generic
   `Exercise1`/`Exercise2` names — under the shared `exercises` package.
2. **Stub style depends on exercise shape:**
   - *Single-method* exercises (a method body to fill in, signature fixed):
     predefine the exact method signature the tests call, with body
     `// TODO: implement this method so the tests in <Name>Test pass`
     followed by `throw new UnsupportedOperationException("not implemented yet");`.
   - *Full-class-design* exercises (fields + constructor + multiple methods,
     typically driven by a UML diagram): the stub is an **empty class with
     only a single TODO comment** pointing at the spec — no field, no
     constructor, no method signatures. Students design the whole class
     themselves. This means `mvn test` fails with a *compile* error until
     implemented, not a runtime exception — that's the expected "red at
     first" state for this stub style, not a mistake to fix.
3. **Tests**: JUnit 5, one test class per exercise class, must not need
   modification by students. Cover the happy path plus edge cases (zero,
   negative, boundary, empty). For methods that print instead of returning
   a value, capture `System.out` with a small `captureOutput`/`captureLines`
   helper (redirect to a `ByteArrayOutputStream`, restore in `finally`)
   rather than changing the method to return a value.
4. **`points.json`**: map each `exercises.<Name>Test` to its point value.
   Note CI overwrites this file from the canonical upstream repo before
   grading (see above), so this only drives local practice/feedback, not
   the real grade — keep it consistent with the README table anyway.
5. **`README.md`** structure (see `2-javaoop-template`'s `README.md` for a
   full example):
   - `# <n>-<topic>` title + one-line welcome paragraph.
   - `## What you'll learn` — a short bullet list of the OOP/language
     concepts the topic's exercises cover (e.g. access modifiers,
     constructors, constrained getters/setters, composition, overriding
     `toString`).
   - `## The exercises` — a table: `Exercise | Name | File | Points`.
   - A short paragraph noting the `TODO`, the test files, and `Main.java`.
   - `## Exercise descriptions` — one `### N. Name (Xp)` subsection per
     exercise, description adapted from `assignments_N.txt`, embedding any
     UML image (`![... UML](uml_xxx.png)`).
   - `## Step by step` — instructs students to use **VS Code's Source
     Control view**, not raw `git` commands: clone via **Clone
     Repository**, run tests via the **Testing** icon/Test Explorer, and at
     the end stage changes with **+**, type a commit message, then use the
     arrow next to **Commit** → **Commit & Push** to commit and push in one
     step. Do **not** mention the GitHub Actions autograder/CI check in the
     student-facing README — that's internal grading plumbing, not
     something students need to act on.
6. `Main.java` stays an untouched placeholder in every topic. If one
   exercise's class is used by another (composition, e.g. `Garage` using
   `Vehicle`), implement the dependency first and say so in this file.
