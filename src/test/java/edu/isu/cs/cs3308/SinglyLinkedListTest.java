package edu.isu.cs.cs3308;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import edu.isu.cs.cs3308.structures.impl.SinglyLinkedList;

/**
 * @author Isaac Griffith
 */
public class SinglyLinkedListTest {

    @Test
    public void testIndexOf_0() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals("First assert: ", 0, list.indexOf(1));
        assertEquals("Second assert: ", 1, list.indexOf(2));
        assertEquals("Third assert: ", 2, list.indexOf(3));
        assertEquals("Fourth assert: ", -1, list.indexOf(null));
        assertEquals("Fifth assert: ", -1, list.indexOf(4));
    }

    @Test
    public void testIndexOf_1() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertEquals("Does not return -1 on empty list: ", -1, list.indexOf(0));
    }

    /**
     * Test if head is updated when inserting at the front.
     */
    @Test
    public void testInsert_01() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(0);
        list.addLast(1);
        list.addLast(2);

        list.insert(3, 0);

        assertEquals("Head not updated: ", (Integer) 3, list.first());
    }

    /**
     * Test is tail is updated when inserting at the back.
     */
    @Test
    public void testInsert_02() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(0);
        list.addLast(1);
        list.addLast(2);

        list.insert(3, list.size() + 1);

        assertEquals("Tail not updated: ", (Integer) 3, list.last());
    }
}
