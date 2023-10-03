import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import libreria.Libreria;
import libreria.Libro;

public class PruebaLibreria {
	
	
//	enum Semana {lun,Mar,Mie,Jue,Vie,Sab,Dom};
//		public static void main(String[] args) {
//			Semana a = Semana.lun;
//			Semana t = Semana.valueOf("Mie");
//			for(Semana se : Semana.values()) {
//				System.out.println(se + " ");
//			}
//		}
	
	public static void main(String[] args) throws FileNotFoundException {
		Libreria libreria = new Libreria();
		File file = new File("/Applications/Eclipse/prLibreria/bin/text.txt");
		Scanner reader = new Scanner(file);
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			String[] s1 = data.replace('"', ' ').replaceAll("[ ( ) ]"," ").strip().split(",");
			
			libreria.addLibro(s1[0].strip(), s1[1].strip(), Double.parseDouble(s1[2]));
		}
		System.out.println(libreria.toString());
		System.out.println("_____________");
		libreria.remLibro("George Orwell", "1984");
		libreria.remLibro("Aldous Huxley", "Un Mundo Feliz");
		libreria.remLibro("Isaac Newton", "Arithmetica Universalis");
		
		System.out.println(libreria.toString());
		
		System.out.println("_____________");
		
		File file1 = new File("/Applications/Eclipse/prLibreria/bin/text2.txt");
		Scanner reader1 = new Scanner(file1);
		while (reader1.hasNextLine()) {
			String data = reader1.nextLine();
			String[] s1 = data.replace('"', ' ').replaceAll("[ ( ) ]"," ").strip().split(",");
			double dd = libreria.getPrecioFinal(s1[0].strip(), s1[1].strip());
			System.out.println("PrecioFinal(" + s1[0] +","+ s1[1] + "): " + dd);
		}		
		Object p = new Object();
		int[] q= new int[20];
		p.toString();
		p.equals(p);
		
		
		
	}
	
}
