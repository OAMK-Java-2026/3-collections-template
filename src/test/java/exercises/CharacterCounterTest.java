package exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterCounterTest {

    @Test
    void countsOccurrencesOfEachCharacter() {
        Map<Character, Integer> result = new CharacterCounter().count("hello");

        assertEquals(4, result.size());
        assertEquals(1, result.get('h'));
        assertEquals(1, result.get('e'));
        assertEquals(2, result.get('l'));
        assertEquals(1, result.get('o'));
    }

    @Test
    void emptyStringReturnsEmptyMap() {
        Map<Character, Integer> result = new CharacterCounter().count("");

        assertTrue(result.isEmpty());
    }

    @Test
    void singleCharacterStringCountsOnce() {
        Map<Character, Integer> result = new CharacterCounter().count("a");

        assertEquals(1, result.size());
        assertEquals(1, result.get('a'));
    }

    @Test
    void distinguishesUpperAndLowerCase() {
        Map<Character, Integer> result = new CharacterCounter().count("Aa");

        assertEquals(2, result.size());
        assertEquals(1, result.get('A'));
        assertEquals(1, result.get('a'));
    }

    @Test
    void countsSpacesAndPunctuationAsCharacters() {
        Map<Character, Integer> result = new CharacterCounter().count("a a!");

        assertEquals(3, result.size());
        assertEquals(2, result.get('a'));
        assertEquals(1, result.get(' '));
        assertEquals(1, result.get('!'));
    }
}
