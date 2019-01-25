package edu.isu.cs.cs3308;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import edu.isu.cs.cs3308.structures.impl.SolitaireEncrypt;

/**
 * @author Isaac Griffith & Blaise Johnson
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
                assertEquals(encrypted.get(i), result);
            }
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Test if deck is generated properly from a file.
     */
    @Test
    public void testGenerateDeckFromFile() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/solitaireEncryptTestFile.txt");
        String sequence = "0 1 2 3 4 5 ";
        String testSequence = encrypt.exportDeckToString();

        assertEquals(sequence, testSequence);
    }

    /**
     * Test if message is properly being converted to numbers.
     */
    @Test
    public void testConvertMessageToNums() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/solitaireEncryptTestFile.txt");
        String testMessage = "ABCDEFG";
        int[] sequence = {1, 2, 3, 4, 5, 6, 7};
        int[] testSequence = encrypt.convertMessageToNums(testMessage);

        for (int i = 0; i < sequence.length; i++) {
            assertEquals("nums don't match: ", sequence[i], testSequence[i]);
        }
    }

    /**
     * Test if triple cut is properly performed.
     */
    @Test
    public void testPerformTripleCut() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/performTripleCutTestFile.txt");

        String sequence = "7 8 9 27 4 5 6 28 1 2 3 ";

        encrypt.performTripleCut();

        String testSequence = encrypt.exportDeckToString();

        assertEquals(sequence, testSequence);
    }

    /**
     * Test if message is properly being converted to numbers.
     */
    @Test
    public void testConvertNumsToMessage() {
        SolitaireEncrypt encrypt = new SolitaireEncrypt("data/solitaireEncryptTestFile.txt");
        int[] sequence = {1, 2, 3, 4, 5, 6, 7};
        String message = "ABCDEFG";
        String testMessage = encrypt.convertNumsToMessage(sequence);

        assertEquals(message, testMessage);
    }
}
