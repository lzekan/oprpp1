package hr.fer.oprpp1.hw05.crypto;


public class Util 
{

	public static byte[] hextobyte(String keyText) {
        if (keyText == null || keyText.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex-encoded string");
        }
        
        int length = keyText.length() / 2;
        byte[] result = new byte[length];
        
        for (int i = 0; i < length; i++) {
            int startIndex = i * 2;
            int endIndex = startIndex + 2;
            String hexByte = keyText.substring(startIndex, endIndex);
            result[i] = (byte) Integer.parseInt(hexByte, 16);
        }
        
        return result;
    }
    
    public static String bytetohex(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : byteArray) {
            int intValue = b & 0xFF;
            String hexValue = Integer.toHexString(intValue);
            
            if (hexValue.length() == 1) {
                hexString.append("0"); // Add leading zero if necessary
            }
            
            hexString.append(hexValue);
        }
        
        return hexString.toString();
    }
	
}
