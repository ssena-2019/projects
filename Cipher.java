
/**
 * Project 2 -- Cipher
 * 
 * This is an implementation of encryption and decryption algorithms of a
 * substitution cipher
 * 
 * @author your name
 * 
 * @class BIL 122
 * 
 * @date date of completion
 * 
 */
import java.util.Scanner;

public class Cipher {

    final String plaintextAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Plaintext
    // Alphabet
    String keyword1; // The Secret Keyword
    String ciphertextAlphabet ; // Ciphertext Alphabet

    /**
     * Class constructor initializes the keyword and creates the Ciphertext Alphabet
     * 
     * @param key the secret keyword used to create the ciphertext alphabet
     */
    public Cipher(String key) {
        key = key.toUpperCase(new java.util.Locale("en", "EN"));
        this.keyword1 = key;
		ciphertextAlphabet = initCiphertextAlphabet();
    }

    /**
     * removes all duplicate occurrences of characters from a String
     * 
     * @param s String with duplicate occurrences of characters
     * @return String with no duplicate characters in it
     */
    public static String removeDups(String s) {
        String result = "";

		char[] array= s.toCharArray();
		
	    for(int i = 0; i < array.length; i++) {
	        if(result.indexOf(array[i]) == -1) 
	            if(array[i] != ' ')
	            	result += array[i];   // add new char
	    }
		return result;	
    }

    /**
     * generates the ciphertext alphabet from the keyword
     * 
     * @return String of ciphertext alphabet generated from the keyword
     */
    public String initCiphertextAlphabet() {
        
		keyword1 = removeDups(keyword1);
		
		String newPlainTextAlphabet = "";

        for (int i = 0; i < plaintextAlphabet.length(); i++) {
        	String a = String.valueOf(plaintextAlphabet.charAt(i));
        	if(keyword1.contains(a)) {
        		//do nothing
        	}else {
        		newPlainTextAlphabet += a;
        	}
        }
        ciphertextAlphabet = keyword1 + newPlainTextAlphabet;
		return ciphertextAlphabet;
    }

    /**
     * Encrypts a message in plaintext
     * 
     * @param message the message to be encrypted in ciphertext alphabet
     * @return the encrypted message in ciphertext alphabet
     */
    public String encrypt(String message) {
        System.out.println(ciphertextAlphabet);
		message = message.toUpperCase(new java.util.Locale("en", "EN"));
		String newMsg = "";

		for(int i = 0; i < message.length(); i++) {
			String a = String.valueOf(message.charAt(i));
			
			if(ciphertextAlphabet.indexOf(a) == -1){
				newMsg = newMsg + a;
			}else {
				int sifreliHarfSira = plaintextAlphabet.indexOf(a);
				newMsg = newMsg + String.valueOf(ciphertextAlphabet.charAt(sifreliHarfSira));
			}
		}
		return newMsg;
    }

    /**
     * Encrypts a message in plaintext
     * 
     * @param ciphertext ciphertext in ciphertext alphabet
     * @return the decrypted message in plaintext alphabet
     */
    public String decrypt(String ciphertext) {
        System.out.println(ciphertextAlphabet);
		ciphertext = ciphertext.toUpperCase(new java.util.Locale("en", "EN"));
		String newMsg = "";
		int msgLength = ciphertext.length();
		
		for(int i = 0; i < msgLength; i++) {
			String a = String.valueOf(ciphertext.charAt(i)); 
			
			if(plaintextAlphabet.indexOf(a) == -1){
				newMsg = newMsg + a;
			}else {
				int sifresizHarfSira = ciphertextAlphabet.indexOf(a);
				newMsg = newMsg + String.valueOf(plaintextAlphabet.charAt(sifresizHarfSira));
			}
		}
		return newMsg;
    }
    
    public static void main(String[] args){
        Scanner scanner =new Scanner(System.in);
		System.out.println("Please enter key: ");
		String key = scanner.nextLine();
		
		Cipher object = new Cipher(key);
				
		System.out.println("Please enter message: ");
		String message = scanner.nextLine();
		System.out.println(object.encrypt(message));
		System.out.println("Please enter keyword: ");
		String keyword = scanner.nextLine();
		System.out.println(object.decrypt(keyword));	
		
		scanner.close();
    }
    
    
}
    

    
    
    
    
    
    

