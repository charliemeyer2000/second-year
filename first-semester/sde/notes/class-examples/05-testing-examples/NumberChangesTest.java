import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberChangesTest {
    @Test
    void constructor_InitialValue_5() {
        var testNumberChanges = new NumberChanges(5);
        assertEquals(5, testNumberChanges.getNumber());
        assertEquals(0, testNumberChanges.getTimesChanged());
    }

    @Test
    void constructor_InitialValue_ZeroArg() {
        var testNumberChanges = new NumberChanges();
        assertEquals(0, testNumberChanges.getNumber());
        assertEquals(0, testNumberChanges.getTimesChanged());
    }

    @Test
    void setNumber_differentNumber() {
        var testNumberChanges = new NumberChanges(5);
        testNumberChanges.setNumber(6);
        assertEquals(6, testNumberChanges.getNumber());
        assertEquals(1, testNumberChanges.getTimesChanged());
    }

}