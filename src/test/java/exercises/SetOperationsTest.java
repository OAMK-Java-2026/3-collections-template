package exercises;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetOperationsTest {

    @Test
    void unionCombinesBothSetsWithoutDuplicates() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = new SetOperations(s1, s2).union();

        assertEquals(new HashSet<>(java.util.List.of(1, 2, 3, 4, 5)), result);
    }

    @Test
    void intersectionReturnsOnlySharedElements() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = new SetOperations(s1, s2).intersection();

        assertEquals(new HashSet<>(java.util.List.of(3)), result);
    }

    @Test
    void intersectionOfDisjointSetsIsEmpty() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4));

        HashSet<Integer> result = new SetOperations(s1, s2).intersection();

        assertEquals(new HashSet<>(), result);
    }

    @Test
    void differenceReturnsElementsOnlyInFirstSet() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(3, 4, 5));

        HashSet<Integer> result = new SetOperations(s1, s2).difference();

        assertEquals(new HashSet<>(java.util.List.of(1, 2)), result);
    }

    @Test
    void differenceOfIdenticalSetsIsEmpty() {
        HashSet<Integer> s1 = new HashSet<>(java.util.List.of(1, 2, 3));
        HashSet<Integer> s2 = new HashSet<>(java.util.List.of(1, 2, 3));

        HashSet<Integer> result = new SetOperations(s1, s2).difference();

        assertEquals(new HashSet<>(), result);
    }

    @Test
    void operationsOnEmptySetsReturnEmptySets() {
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();

        SetOperations setOperations = new SetOperations(s1, s2);

        assertEquals(new HashSet<>(), setOperations.union());
        assertEquals(new HashSet<>(), setOperations.intersection());
        assertEquals(new HashSet<>(), setOperations.difference());
    }
}
