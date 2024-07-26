# Encryption-and-Decryption-with-Embedded-Key-

Description:
This Java program provides a robust utility for performing AES (Advanced Encryption Standard) encryption and decryption on text inputs. The key features of this program include secure encryption, automatic key management, and Base64 encoding for safe text representation.

Key Features:
AES Encryption and Decryption:

Utilizes the AES algorithm, a symmetric encryption method known for its security and efficiency.
Ensures that encrypted text is safely encoded and decoded, making it suitable for transmission or storage.
Embedded Key Management:

The encryption key is embedded within the encrypted text. This approach simplifies the decryption process, as the key is automatically extracted and used.
This method eliminates the need for separate key storage or manual key entry during decryption.
Base64 Encoding and Decoding:

After encryption, the text is encoded in Base64 format. Base64 encoding converts binary data into ASCII text, which is safer for text-based storage and transmission.
During decryption, Base64 decoding is applied to convert the encoded text back to its original binary form before decryption.
User Interaction:

Encryption Mode: Prompts the user to input the text for encryption. It generates a secure AES key, encrypts the text, embeds the key, and outputs the Base64 encoded encrypted text.
Decryption Mode: Requires the user to input the Base64 encoded encrypted text. It automatically extracts the key embedded in the text, decrypts the content, and displays the original text.
Error Handling:

The program includes basic error handling for scenarios such as incorrect Base64 encoding or decryption issues, providing user-friendly error messages.
How It Works:
Encryption:

The user provides plain text.
The program generates a secure AES key and encrypts the text using this key.
The encrypted text is embedded with the key and encoded in Base64 format.
The encoded text and the key are displayed to the user.
Decryption:

The user provides the Base64 encoded encrypted text and the key (automatically extracted from the text).
The program decodes the Base64 text, extracts the key, and decrypts the text using AES.
The original text is then displayed to the user.
Sample Usage:
Encrypting Text:

Choose encryption mode.
Input the text to encrypt.
The program outputs the Base64 encoded encrypted text along with the key used.
Decrypting Text:

Choose decryption mode.
Input the Base64 encoded encrypted text.
The program automatically extracts the key, decrypts the text, and displays the original message.
