package exercises;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetOperationsTest {

    // Looked up via reflection instead of `new SetOperations(...)` / calling
    // union()/intersection()/difference() directly, so this file compiles
    // (and every other exercise's tests keep running) even before
    // SetOperations is implemented — a missing constructor/method just fails
    // the individual test below instead of failing test-compile for
    // everyone.
    private static SetOperations newSetOperations(HashSet<Integer> s1, HashSet<Integer> s2) {
        try {
            Constructor<SetOperations> constructor =
                    SetOperations.class.getDeclaredConstructor(HashSet.class, HashSet.class);
            constructor.setAccessible(true);
            return constructor.newInstance(s1, s2);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "SetOperations(HashSet<Integer> s1, HashSet<Integer> s2) constructor is missing", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static HashSet<Integer> invoke(SetOperations setOperations, String methodName) {
        try {
            Method method = SetOperations.class.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return (HashSet<Integer>) method.invoke(setOperations);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("SetOperations." + methodName + "() method is missing", e);
        }
    }

    private static HashSet<Integer> union(SetOperations setOperations) {
        return invoke(setOperations, "union");
    }

    private static HashSet<Integer> intersection(SetOperations setOperations) {
        return invoke(setOperations, "intersection");
    }

    private static HashSet<Integer> difference(SetOperations setOperations) {
        return invoke(setOperations, "difference");
    }

    @Test
    void unionCombinesBothSetsWithoutDuplicates() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = union(newSetOperations(s1, s2));

        assertEquals(new HashSet<>(java.util.List.of(1, 2, 3, 4, 5)), result);
    }

    @Test
    void intersectionReturnsOnlySharedElements() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = intersection(newSetOperations(s1, s2));

        assertEquals(new HashSet<>(java.util.List.of(3)), result);
    }

    @Test
    void intersectionOfDisjointSetsIsEmpty() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4));

        HashSet<Integer> result = intersection(newSetOperations(s1, s2));

        assertEquals(new HashSet<>(), result);
    }

    @Test
    void differenceReturnsElementsOnlyInFirstSet() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = difference(newSetOperations(s1, s2));

        assertEquals(new HashSet<>(java.util.List.of(1, 2)), result);
    }

    @Test
    void differenceOfIdenticalSetsIsEmpty() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(1, 2, 3));

        HashSet<Integer> result = difference(newSetOperations(s1, s2));

        assertEquals(new HashSet<>(), result);
    }

    @Test
    void operationsOnEmptySetsReturnEmptySets() {
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();

        SetOperations setOperations = newSetOperations(s1, s2);

        assertEquals(new HashSet<>(), union(setOperations));
        assertEquals(new HashSet<>(), intersection(setOperations));
        assertEquals(new HashSet<>(), difference(setOperations));
    }
}
