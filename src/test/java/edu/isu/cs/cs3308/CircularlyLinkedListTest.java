/**
 * Blaise Johnson
 * CS 3308
 * Isaac Griffith
 * 1/24/19
 */
package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import edu.isu.cs.cs3308.structures.impl.List;
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

        assertNull("Case for negative arguments not handled: ",fixture.get(-1));
    }

    /**
     * Test basic functionality of swapWithNext().
     */
    @Test
    public void swapWithNextTest_01() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(4);

        int[] expectedSequence = {0, 2, 1, 3, 4};

        ((CircularlyLinkedList) fixture).swapWithNext(1, 1);

        for (int i = 0; i < fixture.size(); i++) {
            System.out.println(String.format("%d %d", expectedSequence[i], fixture.get(i)));
            assertEquals((Integer) expectedSequence[i], fixture.get(i));
        }
    }

    /**
     * Test the recursive functionality of swapWithNext().
     */
    @Test
    public void swapWithNextTest_02() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(4);

        int[] expectedSequence = {0, 2, 3, 1, 4};

        ((CircularlyLinkedList) fixture).swapWithNext(1, 2);

        for (int i = 0; i < fixture.size(); i++) {
            System.out.println(String.format("%d %d", expectedSequence[i], fixture.get(i)));
            assertEquals((Integer) expectedSequence[i], fixture.get(i));
        }
    }

    /**
     * Test whether tail is updated during swapWithNext().
     */
    @Test
    public void swapWithNextTest_03() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(4);

        int[] expectedSequence = {0, 2, 3, 4, 1};

        ((CircularlyLinkedList) fixture).swapWithNext(1, 3);

        for (int i = 0; i < fixture.size(); i++) {
            System.out.println(String.format("%d %d", expectedSequence[i], fixture.get(i)));
            assertEquals((Integer) expectedSequence[i], fixture.get(i));
        }


        assertEquals("Tail wasn't updated: ", (Integer) expectedSequence[4], fixture.last());
    }

    /**
     * Test whether head and tail are updated during swapWithNext().
     */
    @Test
    public void swapWithNextTest_04() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(4);

        int[] expectedSequence = {1, 2, 3, 4, 0};

        ((CircularlyLinkedList) fixture).swapWithNext(1, 4);

        for (int i = 0; i < fixture.size(); i++) {
            System.out.println(String.format("%d %d", expectedSequence[i], fixture.get(i)));
            assertEquals((Integer) expectedSequence[i], fixture.get(i));
        }

        assertEquals("Head wasn't updated: ", (Integer) expectedSequence[0], fixture.first());
        assertEquals("Tail wasn't updated: ", (Integer) expectedSequence[4], fixture.last());
    }

    /**
     * Test whether head is updated during swapWithNext().
     */
    @Test
    public void swapWithNextTest_05() {
        fixture.addLast(0);
        fixture.addLast(1);
        fixture.addLast(2);
        fixture.addLast(3);
        fixture.addLast(4);

        int[] expectedSequence = {2, 1, 3, 4, 0};

        ((CircularlyLinkedList) fixture).swapWithNext(1, 5);

        for (int i = 0; i < fixture.size(); i++) {
            System.out.println(String.format("%d %d", expectedSequence[i], fixture.get(i)));
            assertEquals((Integer) expectedSequence[i], fixture.get(i));
        }


        assertEquals("Head wasn't updated: ", (Integer) expectedSequence[0], fixture.first());
    }
}