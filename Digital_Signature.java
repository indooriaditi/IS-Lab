import java.security.KeyPair; 
import java.security.KeyPairGenerator; 
import java.security.PrivateKey; 
import java.security.PublicKey; 
import java.security.SecureRandom; 
import java.security.Signature; 
import java.util.Scanner; 
  
import javax.xml.bind.DatatypeConverter; 
  
public class Digital_Signature { 
  
    // Signing Algorithm 
    private static final String 
        SIGNING_ALGORITHM 
        = "SHA256withRSA"; 
    private static final String RSA = "RSA"; 
    private static Scanner sc; 
  
    // Function to implement Digital signature 
    // using SHA256 and RSA algorithm 
    // by passing private key. 
    public static byte[] Create_Digital_Signature( 
        byte[] input, 
        PrivateKey Key) 
        throws Exception 
    { 
        Signature signature 
            = Signature.getInstance( 
                SIGNING_ALGORITHM); 
        signature.initSign(Key); 
        signature.update(input); 
        return signature.sign(); 
    } 
  
    // Generating the asymmetric key pair 
    // using SecureRandom class 
    // functions and RSA algorithm. 
    public static KeyPair Generate_RSA_KeyPair() 
        throws Exception 
    { 
        SecureRandom secureRandom 
            = new SecureRandom(); 
        KeyPairGenerator keyPairGenerator 
            = KeyPairGenerator 
                  .getInstance(RSA); 
        keyPairGenerator 
            .initialize( 
                2048, secureRandom); 
        return keyPairGenerator 
            .generateKeyPair(); 
    } 
  
    // Function for Verification of the 
    // digital signature by using the public key 
    public static boolean
    Verify_Digital_Signature( 
        byte[] input, 
        byte[] signatureToVerify, 
        PublicKey key) 
        throws Exception 
    { 
        Signature signature 
            = Signature.getInstance( 
                SIGNING_ALGORITHM); 
        signature.initVerify(key); 
        signature.update(input); 
        return signature 
            .verify(signatureToVerify); 
    } 
  
    // Driver Code 
    public static void main(String args[]) throws Exception
        
    { 
         Scanner myobj = new Scanner(System.in);  // Create a Scanner object
                  System.out.println("Enter plaintext");
                  String input= myobj.nextLine(); 
  
         
      KeyPair keyPair 
            = Generate_RSA_KeyPair(); 
  
        // Function Call 
        byte[] signature 
            = Create_Digital_Signature( 
                input.getBytes(), 
                keyPair.getPrivate()); 
  
        System.out.println( 
            "Signature Value:\n "
            + DatatypeConverter 
                  .printHexBinary(signature)); 
  
        System.out.println( 
            "Verification: "
            + Verify_Digital_Signature( 
                  input.getBytes(), 
                  signature, keyPair.getPublic())); 
    } 
} 




/*
 * 
 * Description:
 * 
 * Digital Signatures are an Asymmetrically encrypted hash of a digital
 * message(data). It is a value that can provide a guarantee of authenticity,
 * non-repudiation, and integrity. In other terms, it means you can verify the
 * sender, date & time and message content have not been revealed or
 * compromised.
 * 
 * Digital Signatures are often calculated using elliptical curve cryptography,
 * especially in IoT devices, but we will be using RSA for demonstration
 * purposes. First, we will take the input message and create a hash of it using
 * SHA-256 because of its speed and security, and we will then encrypt that hash
 * with the private key from Asymmetric key pair. On the other side, the
 * receiver will decrypt it using the public key and compare the hash to ensure
 * they are indeed the same.
 * 
 * https://www.geeksforgeeks.org/java-implementation-of-digital-signatures-in-
 * cryptography/
 * 
 * 
 * 
 */