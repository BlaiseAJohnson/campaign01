package edu.isu.cs.cs3308.structures.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SolitaireDecrypt {
    /**
     * Represents the deck of cards used in the solitaire encryption method.
     */
    private CircularlyLinkedList<Integer> deck;



    /**
     * Creates a new instance of the SolitaireEncrypt class.
     * @param filePath The file out of which the "deck of cards" will be generated.
     */
    public SolitaireDecrypt(String filePath) {
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

            // Split the String found in the file into elements, then convert those elements into Integers and
            // add them to the deck.
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



    /**
     * Encrypts the given message.
     * @param message The message to encrypt.
     * @return The encrypted message.
     */
    public String execute(String message) {
        // 0. Remove spaces and convert to uppercase
        // 1. Convert message in a sequence of numbers
        // 2. Alter numbers using key stream
        // 3. Convert numbers into letters and combine into a string

        String messageUpperCase = message.toUpperCase();
        String messageNoSpaces = messageUpperCase.replace(" ", "");

        int[] messageSequence = convertMessageToNums(messageNoSpaces);
        int[] encryptedSequence = encrypt(messageSequence);
        String encryptedMessage = convertNumsToMessage(encryptedSequence);

        return encryptedMessage;
    }



    /**
     * Converts a String to an array of Integers where each char is a number 1 - 26.
     * @param message The message to convert.
     * @return The converted message.
     */
    public int[] convertMessageToNums(String message) {
        int[] sequence = new int[message.length()];

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) > 64 && message.charAt(i) <= 90) {
                sequence[i] = (int) message.charAt(i) - 64;
            }
            else {
                sequence[i] = 88 - 64; // 88 is the ascii value for 'X'
            }
        }

        return sequence;
    }



    public String convertNumsToMessage(int[] sequence) {

        StringBuilder messageBuilder = new StringBuilder();

        for (int asciiCode : sequence) {
            messageBuilder.append((char) (asciiCode + 64));
        }

        return messageBuilder.toString();
    }



    public int[] encrypt(int[] sequence) {
        int[] encryptedSequence = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            int key = generateKeystreamValue();
            int currentCode = sequence[i];

            currentCode -= key;
            if (currentCode < 0) {
                currentCode += 26;
            }

            encryptedSequence[i] = currentCode;
        }

        return encryptedSequence;
    }

    /**
     * Implements the Solitaire encryption algorithm.
     * @return A keystream value generated by the algorithm.
     */
    public int generateKeystreamValue() {
        int keyValue;

        do {
            System.out.println(exportDeckToString());

            // 1. Move Joker A 1 card down in the deck.
            int jokerALocation = deck.indexOf(27);
            int jokerA = deck.remove(jokerALocation);
            deck.insert(jokerA, jokerALocation + 1);

            System.out.println(exportDeckToString());

            // 2. Move Joker B two cards down in the deck.
            int jokerBLocation = deck.indexOf(28);
            int jokerB = deck.remove(jokerBLocation);
            deck.insert(jokerB, jokerBLocation + 2);

            System.out.println(exportDeckToString());

            // 3. Perform triple cut.
            performTripleCut();

            System.out.println(exportDeckToString());

            // 4.1 Remove the bottom card from the deck.
            int bottomCard = deck.removeLast();

            System.out.println(exportDeckToString());

            // 4.2 Move cards from the top to the bottom of the deck equal
            //     to the value of the bottom card.
            //     If the bottom card is either joker, count 27 cards.
            int numberOfCards = (bottomCard == 27 || bottomCard == 28) ? 27 : bottomCard;

            for (int i = 0; i < numberOfCards; i++) {
                int currentCard = deck.removeFirst();
                deck.addLast(currentCard);
            }

            System.out.println(exportDeckToString());

            // 4.3 Return the bottom card to the bottom of the deck.
            deck.addLast(bottomCard);

            System.out.println(exportDeckToString());

            // 5.1 Look at the value of the top card.
            //     (If it is either joker, the value is 27)
            int topCard = deck.get(0);
            topCard = (topCard == 27 || topCard == 28)? 27 : topCard;


            // 5.2 Count down a number of cards equal to the value of the top card.
            //     The value of the NEXT card is the key.

            //     (Since the list has a 0-based index, but there is no "0" card, passing the value of
            //      the top card directly int the get() method should return the value of next card.
            keyValue = deck.get(topCard);

            System.out.println(exportDeckToString());
            System.out.println(keyValue);

            // 5.3 If the key value is a joker (27 or 28) repeat this process until it isn't.
        }while (keyValue == 27 || keyValue == 28);

        System.out.println("------------------------------------------");

        return keyValue;
    }



    public void performTripleCut() {
        // Group A will be added to the end so original card order is maintained (addLast).
        CircularlyLinkedList<Integer> cardGroupA = new CircularlyLinkedList<>();

        // Group B will be added to the front so card order must be reversed (addFirst).
        CircularlyLinkedList<Integer> cardGroupB = new CircularlyLinkedList<>();

        // 1. Remove cards from the top of the deck and add them to Group A until a Joker is hit.
        //    (Do not remove the Joker)
        while(cardGroupA.size() < 28) {
            int currentCard = deck.removeFirst();

            if (currentCard == 27 || currentCard == 28) {
                deck.addFirst(currentCard);
                break;
            }
            else {
                cardGroupA.addLast(currentCard);
            }
            // The cards in Group A are in the same order as they were in the deck.
        }

        // 2. Remove cards from the bottom of the deck and add them to Group B until a Joker is hit.
        //    (Do not remove the Joker)
        while(cardGroupB.size() < 28) {
            int currentCard = deck.removeLast();

            if (currentCard == 27 || currentCard == 28) {
                deck.addLast(currentCard);
                break;
            }
            else {
                cardGroupB.addLast(currentCard);
            }
            // The cards in Group B are in reverse order as they were in the deck.
        }

        // 3. Add Group A to the back of the deck.
        while (cardGroupA.size() > 0) {
            int currentCard = cardGroupA.removeFirst();
            deck.addLast(currentCard);
        }

        // 4. Add Group B to the front of the list.
        //    (Taking care to re-reverse the order)
        while (cardGroupB.size() > 0) {
            int currentCard = cardGroupB.removeFirst();
            deck.addFirst(currentCard);
        }
    }


    public String exportDeckToString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < deck.size(); i++) {
            listBuilder.append(deck.get(i)).append(" ");
        }

        return listBuilder.toString();
    }
}
