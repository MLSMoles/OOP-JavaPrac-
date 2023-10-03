
import java.io.FileNotFoundException;
import java.io.IOException;

import hospital.HospitalException;
import hospital.HospitalPlus;

public class MainHospitalPlus {

	public static void main(String[] args) throws IOException {
		try {
			HospitalPlus m = new HospitalPlus("Hospital Regional Universitario de Malaga", 50, 20);
			m.leePacientes("pacientes.txt");
			System.out.println(m.pacientesPorAno());
			System.out.println(m.numeroDePacientesPorAno());
		} catch (HospitalException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

/**
 * Expected output:
 * 
 * <pre>
{1678=[Paciente [nombre=Antonio, apellidos=Vivaldi, nuss=8414193157, anoDeNacimiento=1678]], 1723=[Paciente [nombre=Adam, apellidos=Smith, nuss=5186046392, anoDeNacimiento=1723]], 1743=[Paciente [nombre=Antonio, apellidos=Lavoisier, nuss=1566876261, anoDeNacimiento=1743]], 1775=[Paciente [nombre=Andre-Marie, apellidos=Ampere, nuss=7276995671, anoDeNacimiento=1775]], 1781=[Paciente [nombre=Andres, apellidos=Bello, nuss=3992731073, anoDeNacimiento=1781]], 1788=[Paciente [nombre=Arthur, apellidos=Schopenhauer, nuss=2805848874, anoDeNacimiento=1788]], 1802=[Paciente [nombre=Alejandro, apellidos=Dumas, nuss=6503158367, anoDeNacimiento=1802]], 1809=[Paciente [nombre=Abraham, apellidos=Lincoln, nuss=6669444861, anoDeNacimiento=1809]], 1854=[Paciente [nombre=Arthur, apellidos=Rimbaud, nuss=9462931693, anoDeNacimiento=1854]], 1860=[Paciente [nombre=Antonio, apellidos=Chekhov, nuss=6313288345, anoDeNacimiento=1860]], 1870=[Paciente [nombre=Amado, apellidos=Nervo, nuss=4413957112, anoDeNacimiento=1870]], 1875=[Paciente [nombre=Antonio, apellidos=Machado, nuss=0673932455, anoDeNacimiento=1875], Paciente [nombre=Albert, apellidos=Schweitzer, nuss=7030339829, anoDeNacimiento=1875]], 1879=[Paciente [nombre=Albert, apellidos=Einstein, nuss=7104083471, anoDeNacimiento=1879]], 1881=[Paciente [nombre=Alexander, apellidos=Kerensky, nuss=9051869743, anoDeNacimiento=1881]], 1889=[Paciente [nombre=Adolfo, apellidos=Hitler, nuss=5465995264, anoDeNacimiento=1889]], 1890=[Paciente [nombre=Agatha, apellidos=Chriestie, nuss=2895510826, anoDeNacimiento=1890]], 1896=[Paciente [nombre=Andre, apellidos=Breton, nuss=8365764592, anoDeNacimiento=1896]], 1898=[Paciente [nombre=Alonso, apellidos=Damaso, nuss=3885431592, anoDeNacimiento=1898]], 1899=[Paciente [nombre=Alfred, apellidos=Hitchcock, nuss=3635336290, anoDeNacimiento=1899], Paciente [nombre=Al, apellidos=Capone, nuss=3946671130, anoDeNacimiento=1899]], 1900=[Paciente [nombre=Agustin, apellidos=Lara, nuss=2876703738, anoDeNacimiento=1900]], 1908=[Paciente [nombre=Atahualpa, apellidos=Yupanqui, nuss=7263307860, anoDeNacimiento=1908]], 1913=[Paciente [nombre=Albert, apellidos=Camus, nuss=1710002791, anoDeNacimiento=1913]], 1915=[Paciente [nombre=Anthony, apellidos=Quinn, nuss=1435164914, anoDeNacimiento=1915], Paciente [nombre=Augusto, apellidos=Pinochet, nuss=5247602491, anoDeNacimiento=1915]], 1916=[Paciente [nombre=Aldo, apellidos=Moro, nuss=3745187275, anoDeNacimiento=1916]], 1923=[Paciente [nombre=Allen, apellidos=Ginsberg, nuss=5439262872, anoDeNacimiento=1923], Paciente [nombre=Alan, apellidos=Chepard, nuss=6489579680, anoDeNacimiento=1923]], 1926=[Paciente [nombre=Allan, apellidos=Greenspan, nuss=3546896340, anoDeNacimiento=1926]], 1927=[Paciente [nombre=Antonio Carlos, apellidos=Jobim, nuss=2344845664, anoDeNacimiento=1927]], 1928=[Paciente [nombre=Ariel, apellidos=Sharon, nuss=5460434336, anoDeNacimiento=1928]], 1931=[Paciente [nombre=Adolfo, apellidos=Perez-Esquivel, nuss=0837990171, anoDeNacimiento=1931]], 1937=[Paciente [nombre=Anthony, apellidos=Hopkins, nuss=8059089594, anoDeNacimiento=1937]], 1938=[Paciente [nombre=Annan, apellidos=Kofi, nuss=9428986747, anoDeNacimiento=1938]], 1940=[Paciente [nombre=Al, apellidos=Pacino, nuss=7611603235, anoDeNacimiento=1940]], 1943=[Paciente [nombre=Angelica, apellidos=Maria, nuss=5038997426, anoDeNacimiento=1943]], 1947=[Paciente [nombre=Arnold, apellidos=Schwarzenegger, nuss=7215395217, anoDeNacimiento=1947]], 1948=[Paciente [nombre=Al, apellidos=Gore, nuss=4262893352, anoDeNacimiento=1948]], 1951=[Paciente [nombre=Anatoly, apellidos=Karpov, nuss=5057523820, anoDeNacimiento=1951]], 1954=[Paciente [nombre=Angela, apellidos=Merkel, nuss=3856312192, anoDeNacimiento=1954]], 1960=[Paciente [nombre=Ayrton, apellidos=Senna, nuss=3262532049, anoDeNacimiento=1960], Paciente [nombre=Antonio, apellidos=Banderas, nuss=7169050132, anoDeNacimiento=1960]], 1968=[Paciente [nombre=Ashley, apellidos=Judd, nuss=7922298376, anoDeNacimiento=1968]], 1971=[Paciente [nombre=Adriana, apellidos=Karenbeu, nuss=0561128832, anoDeNacimiento=1971]], 1975=[Paciente [nombre=Angelina, apellidos=Jolie, nuss=8167807396, anoDeNacimiento=1975]], 1981=[Paciente [nombre=Alicia, apellidos=Keys, nuss=4488106841, anoDeNacimiento=1981], Paciente [nombre=Anna, apellidos=Kournikova, nuss=8418100035, anoDeNacimiento=1981], Paciente [nombre=Alessandra, apellidos=Ambrosio, nuss=8993637613, anoDeNacimiento=1981]], 1984=[Paciente [nombre=Avril, apellidos=Lavigne, nuss=7867894312, anoDeNacimiento=1984]]}
{1678=1, 1723=1, 1743=1, 1775=1, 1781=1, 1788=1, 1802=1, 1809=1, 1854=1, 1860=1, 1870=1, 1875=2, 1879=1, 1881=1, 1889=1, 1890=1, 1896=1, 1898=1, 1899=2, 1900=1, 1908=1, 1913=1, 1915=2, 1916=1, 1923=2, 1926=1, 1927=1, 1928=1, 1931=1, 1937=1, 1938=1, 1940=1, 1943=1, 1947=1, 1948=1, 1951=1, 1954=1, 1960=2, 1968=1, 1971=1, 1975=1, 1981=3, 1984=1}
 * </pre>
 */