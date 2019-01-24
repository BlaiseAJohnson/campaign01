package edu.isu.cs.cs3308.structures;

import edu.isu.cs.cs3308.structures.impl.SolitaireDecrypt;
import edu.isu.cs.cs3308.structures.impl.SolitaireEncrypt;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;

        // The following block of code was based on code from the following link:
        // https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
        do {
            System.out.println("Would you like to encrypt or decrypt a message? :\n" +
                    "(1) Encrypt\n" +
                    "(2) Decrypt\n" +
                    "(0) Exit\n");

            while (!scanner.hasNextInt()) {
                System.out.println("I didn't understand that!");
                System.out.println("Would you like to encrypt or decrypt a message? :\n" +
                        "(1) Encrypt\n" +
                        "(2) Decrypt\n" +
                        "(0) Exit\n");
                scanner.next();
            }

            userChoice = scanner.nextInt();
        } while (userChoice != 0 && userChoice != 1 && userChoice != 2);

        scanner.skip("\n");

        if (userChoice == 1) {
            SolitaireEncrypt encrypt = new SolitaireEncrypt("data/deck1.txt");

            System.out.println("Please enter the message which you would like to encrypt:\n" +
                    "(ONLY LETTERS WILL BE ENCRYPTED)\n");

            String message = scanner.nextLine();
            String encryptedMessage = encrypt.execute(message);
            System.out.println("Your encrypted message: \n");
            System.out.println(encryptedMessage);
        }
        else if (userChoice == 2) {
            SolitaireDecrypt decrypt = new SolitaireDecrypt("data/deck1.txt");

            System.out.println("Please enter the message which you would like to decrypt:\n" +
                    "(ONLY LETTERS WILL BE DECRYPTED)\n");
            String message = scanner.nextLine();
            String decryptedMessage = decrypt.execute(message);
            System.out.println("Your decrypted message: \n");
            System.out.println(decryptedMessage);
        }
        else {
            System.out.println("Goodbye");
        }
    }
}
