package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import edu.isu.cs.cs3308.structures.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularlyLinkedListTest {

    private List<Integer> fixture;

    @Before
    public void setUp() {
        fixture = new CircularlyLinkedList<>();
    }

    /**
     * Tests if the circular connection is maintained when adding to the end of the list.
     */
    @Test
    public void connectionTest_01() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);

        assertEquals("Incorrect index accessed: ", (Integer) 0, fixture.get(0));
        assertEquals("Incorrect index accessed: ", (Integer) 3, fixture.get(3));
        assertEquals("List not circular: ", (Integer) 0, fixture.get(4));
        assertEquals("List not circular: ", (Integer) 3, fixture.get(7));
    }

    /**
     * Tests if the circular connection is maintained when adding to the front of the list.
     */
    @Test
    public void connectionTest_02() {
        fixture.addFirst(3);
        fixture.addFirst(2);
        fixture.addFirst(1);
        fixture.addFirst(0);

        assertEquals("Incorrect index accessed: ", (Integer) 0, fixture.get(0));
        assertEquals("Incorrect index accessed: ", (Integer) 3, fixture.get(3));
        assertEquals("List not circular: ", (Integer) 0, fixture.get(4));
        assertEquals("List not circular: ", (Integer) 3, fixture.get(7));
    }

    /**
     * Tests if the circular connection is maintained when removing from the font of the list.
     */
    @Test
    public void connectionTest_03() {
        fixture.addLast(0);
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);

        fixture.removeFirst();

        assertEquals("Incorrect index accessed: ", (Integer) 0, fixture.get(0));
        assertEquals("Incorrect index accessed: ", (Integer) 3, fixture.get(3));
        assertEquals("List not circular: ", (Integer) 0, fixture.get(4));
        assertEquals("List not circular: ", (Integer) 3, fixture.get(7));
    }

    /**
     * Tests if the circular connection is maintained when removing from the end of the list.
     */
    @Test
    public void connectionTest_04() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(3);

        fixture.removeLast();

        assertEquals("Incorrect index accessed: ", (Integer) 0, fixture.get(0));
        assertEquals("Incorrect index accessed: ", (Integer) 3, fixture.get(3));
        assertEquals("List not circular: ", (Integer) 0, fixture.get(4));
        assertEquals("List not circular: ", (Integer) 3, fixture.get(7));
    }

    /**
     * Tests if get() returns null when given a negative index.
     */
    @Test
    public void getTest_01() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);

        assertEquals("Case for negative value not handled: ", null, fixture.get(-1));
    }
}