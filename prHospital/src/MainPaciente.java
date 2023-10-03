import hospital.Paciente;
import hospital.HospitalException;

public class MainPaciente {

	public static void main(String[] args) {
		try {
			Paciente c1 = new Paciente("Abraham", "Lincoln", "6669444861", 1809);
			Paciente c2 = new Paciente("Adam", "Smith", "5186046392", 1723);
			Paciente c3 = new Paciente("Adam", "Smith", "5186046392", 1723);
			System.out.printf("%s %ses igual que %s\n", c1, c1.equals(c2) ? "" : "no ", c2);
			System.out.printf("%s %ses igual que %s\n", c2, c2.equals(c3) ? "" : "no ", c3);
			System.out.printf("%s %ses menor que %s\n", c1, c1.compareTo(c2) < 0 ? "" : "no ", c2);
			System.out.printf("%s %ses menor que %s\n", c2, c2.compareTo(c3) < 0 ? "" : "no ", c3);
			new Paciente("Adam", "Smith", "5186046391", 1723);
		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

}

/**
 * Expected output:
 * 
 * <pre>
Paciente [nombre=Abraham, apellidos=Lincoln, nuss=6669444861, anoDeNacimiento=1809] no es igual que Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723]
Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723] es igual que Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723]
Paciente [nombre=Abraham, apellidos=Lincoln, nuss=6669444861, anoDeNacimiento=1809] no es menor que Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723]
Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723] no es menor que Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723]
hospital.HospitalException: NUSS erroneo
	at hospital.Paciente.<init>(Paciente.java:46)
	at MainPaciente.main(MainPaciente.java:15)
 * </pre>
 */