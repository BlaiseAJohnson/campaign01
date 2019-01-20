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

        list.indexOf(0);
    }
}
