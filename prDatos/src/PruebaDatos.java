import java.util.Arrays;

import prDatos.Datos;

public class PruebaDatos {
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(args));
		try {
			double min = Double.parseDouble((args[0]));
			double max = Double.parseDouble((args[1]));
			System.out.println(min);
			System.out.println(max);
			
			Datos d = new Datos(Arrays.copyOfRange(args, 2, args.length), min , max);
			System.out.println(d);
		} catch(NumberFormatException e) {
			System.out.println("Error al convertir a double " + e.getMessage());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Np hay valores");
		}
	}
}
