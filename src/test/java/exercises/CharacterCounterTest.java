package exercises;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterCounterTest {

    // `new CharacterCounter()` always compiles (default no-arg constructor),
    // but `count(String)` doesn't exist until implemented, so it's looked up
    // via reflection instead — this file compiles (and every other
    // exercise's tests keep running) even before CharacterCounter is
    // implemented, and a missing method just fails the individual test below
    // instead of failing test-compile for everyone.
    @SuppressWarnings("unchecked")
    private static Map<Character, Integer> count(CharacterCounter counter, String input) {
        try {
            Method method = CharacterCounter.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);
            return (Map<Character, Integer>) method.invoke(counter, input);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("CharacterCounter.count(String) method is missing", e);
        }
    }

    @Test
    void countsOccurrencesOfEachCharacter() {
        Map<Character, Integer> result = count(new CharacterCounter(), "hello");

        assertEquals(4, result.size());
        assertEquals(1, result.get('h'));
        assertEquals(1, result.get('e'));
        assertEquals(2, result.get('l'));
        assertEquals(1, result.get('o'));
    }

    @Test
    void emptyStringReturnsEmptyMap() {
        Map<Character, Integer> result = count(new CharacterCounter(), "");

        assertTrue(result.isEmpty());
    }

    @Test
    void singleCharacterStringCountsOnce() {
        Map<Character, Integer> result = count(new CharacterCounter(), "a");

        assertEquals(1, result.size());
        assertEquals(1, result.get('a'));
    }

    @Test
    void distinguishesUpperAndLowerCase() {
        Map<Character, Integer> result = count(new CharacterCounter(), "Aa");

        assertEquals(2, result.size());
        assertEquals(1, result.get('A'));
        assertEquals(1, result.get('a'));
    }

    @Test
    void countsSpacesAndPunctuationAsCharacters() {
        Map<Character, Integer> result = count(new CharacterCounter(), "a a!");

        assertEquals(3, result.size());
        assertEquals(2, result.get('a'));
        assertEquals(1, result.get(' '));
        assertEquals(1, result.get('!'));
    }
}
