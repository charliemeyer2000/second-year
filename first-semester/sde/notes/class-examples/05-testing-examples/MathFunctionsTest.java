import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionsTest {

    @Test
    void square() {
        assertEquals(4, MathFunctions.square(2));
    }

    @Test
    void isDivisibleBy_True() {
        assertTrue(MathFunctions.isDivisibleBy(10, 5));
    }

    @Test
    void isDivisibleBy_False() {
        assertFalse(MathFunctions.isDivisibleBy(10, 4));
    }

    @Test
    void isEven_True() {
        assertTrue(MathFunctions.isEven(4));
    }

    @Test
    void isEven_False() {
        assertFalse(MathFunctions.isEven(5));
    }

    @Test
    void isOdd_True() {
        assertTrue(MathFunctions.isOdd(5));
    }

    @Test
    void isOdd_False() {
        assertFalse(MathFunctions.isOdd(4));
    }

    @Test
    void max_2arg_firstValueLarger() {
        assertEquals(5, MathFunctions.max(5, 3));
    }

    @Test
    void max_2arg_secondValueLarger() {
        assertEquals(-2, MathFunctions.max(-7, -2));
    }

    @Test
    void max_2arg_equal() {
        assertEquals(0, MathFunctions.max(0, 0));
    }

    @Test
    void weirdMax_firstValueLarger() {
        assertEquals(5, MathFunctions.max(5, 3));
    }

    @Test
    void weirdMax_secondValueLarger() {
        assertEquals(-2, MathFunctions.max(-2, -7));
    }

    @Test
    void weirdMax_equal() {
        assertEquals(0, MathFunctions.max(0, 0));
    }

    @Test
    void testMax_a_largest() {
        assertEquals(3, MathFunctions.max(3, 2, 1));
    }

    @Test
    void testMax_b_largest() {
        assertEquals(3, MathFunctions.max(2, 3, 1));
    }

    @Test
    void testMax_allEqual() {
        assertEquals(3, MathFunctions.max(3, 3, 3));
    }

}