package exercises;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataOrganizerTest {

    // `new DataOrganizer()` always compiles (default no-arg constructor), but
    // `groupAndSort(ArrayList<Integer>)` doesn't exist until implemented, so
    // it's looked up via reflection instead — this file compiles (and every
    // other exercise's tests keep running) even before DataOrganizer is
    // implemented, and a missing method just fails the individual test below
    // instead of failing test-compile for everyone.
    @SuppressWarnings("unchecked")
    private static HashMap<String, ArrayList<Integer>> groupAndSort(
            DataOrganizer organizer, ArrayList<Integer> numbers) {
        try {
            Method method = DataOrganizer.class.getDeclaredMethod("groupAndSort", ArrayList.class);
            method.setAccessible(true);
            return (HashMap<String, ArrayList<Integer>>) method.invoke(organizer, numbers);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("DataOrganizer.groupAndSort(ArrayList<Integer>) method is missing", e);
        }
    }

    @Test
    void splitsIntoEvenAscendingAndOddDescending() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(5, 2, 8, 1, 4, 3));

        HashMap<String, ArrayList<Integer>> result = groupAndSort(new DataOrganizer(), numbers);

        assertEquals(List.of(2, 4, 8), result.get("Even"));
        assertEquals(List.of(5, 3, 1), result.get("Odd"));
    }

    @Test
    void handlesNegativeNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(-4, -3, -2, -1));

        HashMap<String, ArrayList<Integer>> result = groupAndSort(new DataOrganizer(), numbers);

        assertEquals(List.of(-4, -2), result.get("Even"));
        assertEquals(List.of(-1, -3), result.get("Odd"));
    }

    @Test
    void allEvenNumbersLeavesOddListEmpty() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(6, 2, 4));

        HashMap<String, ArrayList<Integer>> result = groupAndSort(new DataOrganizer(), numbers);

        assertEquals(List.of(2, 4, 6), result.get("Even"));
        assertTrue(result.get("Odd").isEmpty());
    }

    @Test
    void emptyInputReturnsBothKeysWithEmptyLists() {
        ArrayList<Integer> numbers = new ArrayList<>();

        HashMap<String, ArrayList<Integer>> result = groupAndSort(new DataOrganizer(), numbers);

        assertTrue(result.get("Even").isEmpty());
        assertTrue(result.get("Odd").isEmpty());
    }

    @Test
    void keepsDuplicateNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(2, 2, 3, 3));

        HashMap<String, ArrayList<Integer>> result = groupAndSort(new DataOrganizer(), numbers);

        assertEquals(List.of(2, 2), result.get("Even"));
        assertEquals(List.of(3, 3), result.get("Odd"));
    }
}
