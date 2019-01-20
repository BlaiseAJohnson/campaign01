package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import edu.isu.cs.cs3308.structures.impl.SolitaireEncrypt;

/**
 * @author Isaac Griffith
 */
public class SolitaireEncryptTest {

    @Test
    public void testExecute() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/deck1.txt");
        try {
            List<String> encrypted = Files.readAllLines(Paths.get("data/encrypted.txt"));
            List<String> decrypted = Files.readAllLines(Paths.get("data/messages.txt"));

            for (int i = 0; i < encrypted.size(); i++) {
                String result = encrypt.execute(decrypted.get(i));
                assertEquals(result, encrypted.get(i));
            }
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testGenerateDeckFromFile() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/solitaireEncryptTestFile.txt");
        CircularlyLinkedList<Integer> testDeck = new CircularlyLinkedList<>();
        testDeck.addLast(0);
        testDeck.addLast(1);
        testDeck.addLast(2);
        testDeck.addLast(3);
        testDeck.addLast(4);
        testDeck.addLast(5);

        for (int i = 0; i < testDeck.size(); i++) {
            assertEquals("Deck has not been generated properly: ", testDeck.get(i), encrypt.getDeck().get(i));
        }
    }
}
