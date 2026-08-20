package exercises;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FibonacciTest {

    // Looked up via reflection instead of calling the constructor/methods
    // directly, so this file compiles (and every other exercise's tests keep
    // running) even before Fibonacci is implemented — a missing
    // constructor/method just fails the individual test below instead of
    // failing test-compile for everyone. README specifies an Integer
    // parameter throughout, so lookups use Integer.class rather than
    // int.class.
    private static Fibonacci newFibonacci(int n) {
        try {
            Constructor<Fibonacci> constructor = Fibonacci.class.getDeclaredConstructor(Integer.class);
            constructor.setAccessible(true);
            return constructor.newInstance(n);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci(Integer n) constructor is missing", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static Vector<Integer> getNumbers(Fibonacci fibonacci) {
        try {
            Method method = Fibonacci.class.getDeclaredMethod("getNumbers");
            method.setAccessible(true);
            return (Vector<Integer>) method.invoke(fibonacci);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci.getNumbers() method is missing", e);
        }
    }

    private static int addNext(Fibonacci fibonacci) {
        try {
            Method method = Fibonacci.class.getDeclaredMethod("addNext");
            method.setAccessible(true);
            return (int) method.invoke(fibonacci);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci.addNext() method is missing", e);
        }
    }

    private static void addNext(Fibonacci fibonacci, int upTo) {
        try {
            Method method = Fibonacci.class.getDeclaredMethod("addNext", Integer.class);
            method.setAccessible(true);
            method.invoke(fibonacci, upTo);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci.addNext(Integer) method is missing", e);
        }
    }

    private static boolean isFibonacci(Fibonacci fibonacci, int n) {
        try {
            Method method = Fibonacci.class.getDeclaredMethod("isFibonacci", Integer.class);
            method.setAccessible(true);
            return (boolean) method.invoke(fibonacci, n);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci.isFibonacci(Integer) method is missing", e);
        }
    }

    private static boolean compare(Fibonacci fibonacci, Vector<Integer> sequence) {
        try {
            Method method = Fibonacci.class.getDeclaredMethod("compare", Vector.class);
            method.setAccessible(true);
            return (boolean) method.invoke(fibonacci, sequence);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Fibonacci.compare(Vector<Integer>) method is missing", e);
        }
    }

    @Test
    void constructorPopulatesTheFirstNNumbers() {
        Fibonacci fibonacci = newFibonacci(8);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13)), getNumbers(fibonacci));
    }

    @Test
    void constructorWithZeroLeavesTheSequenceEmpty() {
        Fibonacci fibonacci = newFibonacci(0);

        assertTrue(getNumbers(fibonacci).isEmpty());
    }

    @Test
    void constructorWithOneStoresOnlyTheFirstNumber() {
        Fibonacci fibonacci = newFibonacci(1);

        assertEquals(new Vector<>(java.util.List.of(0)), getNumbers(fibonacci));
    }

    @Test
    void addNextAppendsAndReturnsTheNextNumber() {
        Fibonacci fibonacci = newFibonacci(8);

        int next = addNext(fibonacci);

        assertEquals(21, next);
        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13, 21)), getNumbers(fibonacci));
    }

    @Test
    void addNextWithParameterExtendsSequenceUpToGivenValueInclusive() {
        Fibonacci fibonacci = newFibonacci(4);

        addNext(fibonacci, 13);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8, 13)), getNumbers(fibonacci));
    }

    @Test
    void addNextWithNonFibonacciParameterStopsBeforeExceedingIt() {
        Fibonacci fibonacci = newFibonacci(4);

        addNext(fibonacci, 10);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5, 8)), getNumbers(fibonacci));
    }

    @Test
    void addNextWithParameterNotBiggerThanLastNumberLeavesSequenceUnchanged() {
        Fibonacci fibonacci = newFibonacci(6);

        addNext(fibonacci, 3);

        assertEquals(new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5)), getNumbers(fibonacci));
    }

    @Test
    void isFibonacciRecognizesFibonacciNumbers() {
        Fibonacci fibonacci = newFibonacci(0);

        assertTrue(isFibonacci(fibonacci, 0));
        assertTrue(isFibonacci(fibonacci, 1));
        assertTrue(isFibonacci(fibonacci, 13));
        assertTrue(isFibonacci(fibonacci, 21));
    }

    @Test
    void isFibonacciRejectsNonFibonacciNumbers() {
        Fibonacci fibonacci = newFibonacci(0);

        assertFalse(isFibonacci(fibonacci, 4));
        assertFalse(isFibonacci(fibonacci, 6));
        assertFalse(isFibonacci(fibonacci, -1));
    }

    @Test
    void compareAcceptsAValidFibonacciSequence() {
        Fibonacci fibonacci = newFibonacci(0);

        assertTrue(compare(fibonacci, new Vector<>(java.util.List.of(0, 1, 1, 2, 3, 5))));
    }

    @Test
    void compareRejectsAnIncorrectSequence() {
        Fibonacci fibonacci = newFibonacci(0);

        assertFalse(compare(fibonacci, new Vector<>(java.util.List.of(0, 1, 2, 3))));
    }

    @Test
    void compareAcceptsAnEmptySequence() {
        Fibonacci fibonacci = newFibonacci(0);

        assertTrue(compare(fibonacci, new Vector<>()));
    }
}
