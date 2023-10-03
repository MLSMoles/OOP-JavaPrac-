import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class test {

	public static void main(String[] args) throws Exception{
		Laberinto lab = new Laberinto(60,60);
		
		//lab.generarLaberinto(getMd5("b"));
		lab.generarLaberinto();
		System.out.println("Laberinto resuelto");
		lab.mostrarLaberinto();
		
		lab.solveLaberynth();
		System.out.println();
		System.out.println("________________________________________________");
		System.out.println("Array de solución:");
		ArrayList<Nodo> nn = lab.solucion();
		System.out.println(nn.toString());
		System.out.println("________________________________________________");
		System.out.println();
		
		lab.pintarSolucion(nn);
		lab.mostrarLaberinto();
		System.out.println();
		System.out.println("Seed: " + lab.Seed());
		
		//seed interesante b8d050f0c48c66c971d2ab15adec90d3
	}
	
	
	
	
	
	
	private static String getMd5(String input)
    {
        try {
 
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

