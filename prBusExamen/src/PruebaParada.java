import bus.BusException;
import bus.Parada;

public class PruebaParada {

	public static void main(String[] args) {
		 try {
		Parada p1 = new Parada("Paseo del Parque", 36.71884, -4.41910);
        Parada p2 = new Parada("paseo del parque", 36.71884, -4.41910);
        
        System.out.println("Las paradas " +p1 +" y "+p2 +"Son" +(p1.equals(p2) ? "Iguales":"Diferentes")+".");
       
        Parada p3 = new Parada("Louis Pasteur", 36.71654, -184.47508);
        }catch(BusException b) {
        	System.err.println(b.getMessage());
        }
	}
}
