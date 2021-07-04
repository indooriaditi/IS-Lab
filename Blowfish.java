import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Blowfish
{
  private static final String key="123";
  public static String encrypt(String password)
  {
    try
    {
      byte[] keyData = (key).getBytes();
      SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      byte[] hasil = cipher.doFinal(password.getBytes());
      return new String(Base64.getEncoder().encode(hasil));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
  public static void main(String[] args) {
    System.out.println("your encrypted word is"+ encrypt("vishwa"));
  }
}
