package edu.isu.cs.cs3308.structures.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SolitaireEncrypt {

    /**
     * Represents the deck of cards used in the solitaire encryption method.
     */
    private CircularlyLinkedList<Integer> deck;

    public CircularlyLinkedList<Integer> getDeck() { return deck; }

    /**
     * Creates a new instance of the SolitaireEncrypt class.
     * @param filePath The file out of which the "deck of cards" will be generated.
     */
    public SolitaireEncrypt(String filePath) {

        deck = generateDeckFromFile(filePath);
    }


    /**
     * Takes the contents of a file and creates a "deck" of "cards" to be used in the encryption process.
     * @param filePath The file from which the content will be pulled.
     * @return A circularly-linked list containing each "card" in the "deck".
     */
    private CircularlyLinkedList<Integer> generateDeckFromFile(String filePath) {
        try {
            List<String> rawDeck = Files.readAllLines(Paths.get(filePath));
            CircularlyLinkedList<Integer> deck = new CircularlyLinkedList<>();

            if (rawDeck.isEmpty()) return deck;

            String deckString = rawDeck.get(0);
            String[] deckStringElements = deckString.split(" ");
            for (String element : deckStringElements) {
                int elementAsInt = Integer.parseInt(element);
                deck.addLast(elementAsInt);
            }

            return deck;
        }
        catch (IOException e) {
            System.out.println(
                    "Specified file could not be found or does not exist.\n" +
                    "Encryption could not be performed.");
            return new CircularlyLinkedList<>();
        }
    }



    public String execute(String message) {
        return message;
    }
}
