package hr.fer.oprpp1.hw05.crypto;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.*;
import java.io.*;
import java.security.spec.*;

public class Crypto {
	
	private static final int AES_KEY_SIZE = 16; // 128 bits

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java Crypto checksha <filename>");
            System.out.println("       java Crypto encrypt <inputFilename> <outputFilename> <keyText> <ivText>");
            System.out.println("       java Crypto decrypt <inputFilename> <outputFilename> <keyText> <ivText>");
            return;
        }

        String command = args[0];

        if (command.equals("checksha")) {
        	
            String filename = args[1];
            checkSHA256Digest(filename);
            
        } else if (command.equals("encrypt") || command.equals("decrypt")) {
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits): ");
            String keyText = sc.next();
            
            System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits): ");
            String ivText = sc.next();
                        
            String inputFilename = args[1];
            String outputFilename = args[2];

            byte[] keyBytes = Util.hextobyte(keyText);
            byte[] ivBytes = Util.hextobyte(ivText);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivBytes);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(command.equals("encrypt") ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key, paramSpec);

            encryptDecryptFile(inputFilename, outputFilename, cipher, command);
        } else {
            System.out.println("Invalid command.");
        }
        
        
        
    }
    
    private static void encryptDecryptFile(String inputFilename, String outputFilename, Cipher cipher, String command) throws Exception {
    	
        try (InputStream inputStream = new FileInputStream(inputFilename);
             OutputStream outputStream = new FileOutputStream(outputFilename)) {

            byte[] inputBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.update(inputBuffer, 0, bytesRead);
                if (outputBuffer != null) {
                    outputStream.write(outputBuffer);
                }
            }

            byte[] outputBuffer = cipher.doFinal();
            if (outputBuffer != null) {
                outputStream.write(outputBuffer);
            }
        }
        System.out.println("File " + inputFilename + " " +
                (command + "ed") + " to " + outputFilename);
    }
        
        
    private static void checkSHA256Digest(String filename) throws IOException, NoSuchAlgorithmException {
    	
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please provide expected SHA-256 digest for " + filename + ": ");
            String expectedDigestHex = scanner.nextLine().trim().toLowerCase();

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            
            try (InputStream inputStream = new FileInputStream(filename)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    sha256.update(buffer, 0, bytesRead);
                }
            }

            byte[] calculatedDigest = sha256.digest();
            String calculatedDigestHex = Util.bytetohex(calculatedDigest);

            if (calculatedDigestHex.equals(expectedDigestHex)) {
                System.out.println("Digesting completed. Digest of " + filename + " matches expected digest.");
            } else {
                System.out.println("Digesting completed. Digest of " + filename + " does not match the expected digest.");
                System.out.println("Digest was: " + calculatedDigestHex);
            }
        }    
}
