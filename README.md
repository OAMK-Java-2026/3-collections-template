# 3-collections

Welcome! This topic covers the Java Collections Framework through four small
exercises.

## What you'll learn

- Storing unique values with `HashSet` and computing set operations (union,
  intersection, difference)
- Counting and grouping data with `Map`/`HashMap`
- Sorting and organizing values with `List`/`ArrayList`
- Using `Vector` as a growable, sequential collection
- Designing a class's fields, constructor, and methods from a written spec

## The exercises

| Exercise | Name | File | Points |
|---|---|---|---|
| 1 | Set Operations | `src/main/java/exercises/SetOperations.java` | 1 |
| 2 | Character Counter | `src/main/java/exercises/CharacterCounter.java` | 2 |
| 3 | Data Organizer | `src/main/java/exercises/DataOrganizer.java` | 2 |
| 4 | Fibonacci | `src/main/java/exercises/Fibonacci.java` | 3 |

Each exercise has a `TODO` to fill in, and a matching test file you can use
to check your work as you go. You don't need to touch the test files —
they're just there to help you see how you're doing. For **Set Operations**
and **Fibonacci** the class starts out completely empty, so you'll need to
design the fields, constructor, and methods yourself from the description
below — that's expected to show a compile error in the tests until you do.

There's also a `Main.java` with a `main` method, so you have something
runnable from the start — it doesn't do anything yet, it's just there so
your "Run" button works right away.

## Exercise descriptions

### 1. Set Operations (1p)

Let's focus on the three set operations union, intersection, and
difference, applied to two given member sets.

The class `SetOperations` has:

- Private members `HashSet<Integer> set1` and `HashSet<Integer> set2`.
- A constructor `SetOperations(HashSet<Integer> s1, HashSet<Integer> s2)`
  that initializes the two member sets.
- A method `public HashSet<Integer> union()` returning the union of the
  member sets.
- A method `public HashSet<Integer> intersection()` returning the
  intersection of the member sets.
- A method `public HashSet<Integer> difference()` returning the difference
  of the member sets.

Hint: use the `HashSet` class rather than the `Set` interface for the
fields, and brush up on basic set theory if the three operations aren't
familiar.

### 2. Character Counter (2p)

The class `CharacterCounter` counts the number of occurrences of each
character in a string.

It has only the method `public Map<Character, Integer> count(String
inputString)`. The method accepts a `String` and returns a `Map` where the
key is a character and the value is how many times that character occurs
in the string. For example, `count("hello")` returns a map containing
`{e:1, h:1, l:2, o:1}`. An empty input string returns an empty map.

Hint: converting the string to a character array may help, and the `String`
and `HashMap` documentation has methods that can simplify the counting.

### 3. Data Organizer (2p)

This exercise emphasizes `List` and `Map` interface implementations,
focusing on sorting and grouping data.

The class `DataOrganizer` has only the method
`HashMap<String, ArrayList<Integer>> groupAndSort(ArrayList<Integer>
numbers)`. It groups the numbers into two lists within the returned map:

- Key `"Even"` — the even numbers, sorted in ascending order.
- Key `"Odd"` — the odd numbers, sorted in descending order.

### 4. Fibonacci (3p)

Let's continue exploring the Fibonacci sequence.

The class `Fibonacci` has:

- A private `Vector<Integer>` member storing the sequence.
- A constructor with an `Integer` parameter that initializes the sequence
  into the `Vector`. If the parameter is 8, the first 8 Fibonacci numbers
  (indices 0..7) are stored.
- A getter `getNumbers()` for the collection.
- A method `addNext()` that computes the next Fibonacci number, appends it
  to the collection, and returns it.
- An overload `addNext(Integer)` that extends the sequence with the
  missing numbers up to the given size.
- A method `isFibonacci(Integer)` that checks whether a given number is a
  Fibonacci number, returning `true`/`false`.
- A method `compare(Vector<Integer> seq)` that checks whether the given
  sequence is a correct Fibonacci sequence, returning `true`/`false`.

## Step by step

1. **Clone this repo**:
   ```
   git clone <this repo's URL>
   cd <the folder that creates>
   ```
2. **Open it in VS Code**: `code .` (or File → Open Folder). If prompted
   "This workspace has extension recommendations", click **Install** — this
   adds a flask-shaped **Testing** icon to the left sidebar.
3. **Run the tests before changing anything**, just to see where you're
   starting from. Click the flask icon, then the play button at the top of
   the Test Explorer panel — everything will be red at first, and that's
   completely normal.
4. **Implement each exercise** in its source file, one at a time.
5. **Re-run the tests** after each change to see your progress. Prefer a
   terminal? `mvn test` does the same check for all exercises at once.
6. **Work locally** until everything passes.
7. **Push your work back** to the GitHub organization when you're ready.
   In VS Code's **Source Control** view: stage your changes with the **+**
   next to each changed file (or next to "Changes" to stage everything),
   type a commit message in the box at the top, then click the arrow next
   to **Commit** and choose **Commit & Push** to commit and push in one
   step.
8. **Assignment completed — good job!**
