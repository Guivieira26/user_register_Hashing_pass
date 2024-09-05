import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashing {
    //Method to hash a string
    public static String hashString(String input) throws NoSuchAlgorithmException {
        // Obtém a instância do MessageDigest para o algoritmo SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        
        // Hasheia o input e obtém o array de bytes
        byte[] hashBytes = digest.digest(input.getBytes());
        
        // Converte o array de bytes em uma string hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        // Retorna o hash como string
        return hexString.toString();
    }
}
