import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptionExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose an action:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    SecretKey secretKey = generateAESKey();
                    String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

                    System.out.println("Enter the text:");
                    String textToWrite = scanner.nextLine().trim();
                    String encryptedText = encrypt(textToWrite, secretKey);
                    String finalOutput = encodedKey + ":" + encryptedText;
                    System.out.println("\n\n");
                    System.out.println("Encryption completed. Encrypted text: " + finalOutput);
                    break;

                case 2:
                    System.out.println("Enter the encrypted text with key:");
                    String combinedInput = scanner.nextLine().trim();
                    String[] parts = combinedInput.split(":", 2);

                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid input format. Please provide the encrypted text in the format 'key:encryptedText'.");
                    }

                    String encodedKeyInput = parts[0];
                    String encryptedTextForDecryption = parts[1];

                    if (!isValidBase64(encodedKeyInput) || !isValidBase64(encryptedTextForDecryption)) {
                        throw new IllegalArgumentException("Invalid Base64 format.");
                    }

                    SecretKey secretKeyForDecryption = decodeAESKey(encodedKeyInput);

                    String decryptedText = decrypt(encryptedTextForDecryption, secretKeyForDecryption);
                    System.out.println("Decryption completed. Decrypted text: " + decryptedText);
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1 for Encrypt or 2 for Decrypt.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        scanner.close();
    }

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); 
        return keyGen.generateKey();
    }

    public static SecretKey decodeAESKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static String encrypt(String text, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static boolean isValidBase64(String s) {
        if (s == null || s.length() == 0 || s.length() % 4 != 0) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '+' && c != '/' && c != '=') {
                return false;
            }
        }
        return true;
    }
}
