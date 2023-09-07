package edu.virginia.cs.testingintro;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MySortedListTest {

    @Test
    void isEmptyTestInitiallyTrue() {
        MySortedList myList = new MySortedList();
        assertTrue(myList.isEmpty());
    }

    @Test
    void initiallyEmptyGetThrowsExceptions() {
        MySortedList myList = new MySortedList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> myList.get(0));
    }

    @Test
    void isEmptyTestInitiallyFalse() {
        ArrayList<Integer> starterList = new ArrayList<>();
        starterList.add(1);
        MySortedList myList = new MySortedList(starterList);
        assertFalse(myList.isEmpty());
        assertEquals(1, myList.size());
    }

    @Test
    void addValueToMiddle() {
        //create starter List
        ArrayList<Integer> starterList = new ArrayList<>(
                List.of(1, 3, 5));

        MySortedList myList = new MySortedList(starterList);
        myList.add(4); //should be added between 3 and 5

        ArrayList<Integer> expectedList = new ArrayList<>(
                List.of(1, 3, 4, 5));

        assertEquals(expectedList, myList.getSortedList());
    }
}
