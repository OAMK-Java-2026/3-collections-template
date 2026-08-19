package exercises;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FibonacciTest {

    @Test
    void constructorPopulatesTheFirstNNumbers() {
        Fibonacci fibonacci = new Fibonacci(8);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13)), fibonacci.getNumbers());
    }

    @Test
    void constructorWithZeroLeavesTheSequenceEmpty() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertTrue(fibonacci.getNumbers().isEmpty());
    }

    @Test
    void constructorWithOneStoresOnlyTheFirstNumber() {
        Fibonacci fibonacci = new Fibonacci(1);

        assertEquals(new Vector<>(java.util.List.of(0)), fibonacci.getNumbers());
    }

    @Test
    void addNextAppendsAndReturnsTheNextNumber() {
        Fibonacci fibonacci = new Fibonacci(8);

        int next = fibonacci.addNext();

        assertEquals(21, next);
        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13, 21)), fibonacci.getNumbers());
    }

    @Test
    void addNextWithParameterExtendsSequenceToThatSize() {
        Fibonacci fibonacci = new Fibonacci(4);

        fibonacci.addNext(8);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13)), fibonacci.getNumbers());
    }

    @Test
    void addNextWithParameterNotBiggerThanCurrentSizeLeavesSequenceUnchanged() {
        Fibonacci fibonacci = new Fibonacci(6);

        fibonacci.addNext(3);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5)), fibonacci.getNumbers());
    }

    @Test
    void isFibonacciRecognizesFibonacciNumbers() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertTrue(fibonacci.isFibonacci(0));
        assertTrue(fibonacci.isFibonacci(1));
        assertTrue(fibonacci.isFibonacci(13));
        assertTrue(fibonacci.isFibonacci(21));
    }

    @Test
    void isFibonacciRejectsNonFibonacciNumbers() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertFalse(fibonacci.isFibonacci(4));
        assertFalse(fibonacci.isFibonacci(6));
        assertFalse(fibonacci.isFibonacci(-1));
    }

    @Test
    void compareAcceptsAValidFibonacciSequence() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertTrue(fibonacci.compare(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5))));
    }

    @Test
    void compareRejectsAnIncorrectSequence() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertFalse(fibonacci.compare(new Vector<>(java.util.List.of(0, 1, 2, 3))));
    }

    @Test
    void compareAcceptsAnEmptySequence() {
        Fibonacci fibonacci = new Fibonacci(0);

        assertTrue(fibonacci.compare(new Vector<>()));
    }
}
