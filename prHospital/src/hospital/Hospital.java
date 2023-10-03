package hospital;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
public class Hospital {
	protected Map<Paciente,Habitacion> habitaciones;
	protected SortedSet<Habitacion> libres;
	private int plantas;
	private String nombre;
	
	public Hospital(String nombre, int plantas, int habitaciones) throws HospitalException {
		if(nombre == null||nombre.length()==0|| plantas<=0) throw new HospitalException("Error");
		this.plantas = plantas;
		this.nombre = nombre;
		libres = new TreeSet<>();
		this.habitaciones = new TreeMap<>();
		for(int i = 0;i<plantas;i++) {
			for(int k = 0;k<habitaciones;k++) {
				Habitacion aux = new Habitacion(i,k);
				libres.add(aux);
			}
		}
	}
	
	public void alta(Paciente p) throws HospitalException{
		if(p==null) throw new HospitalException("Paciente nullo!");
		if(libres.isEmpty()) throw new HospitalException("Hospital Lleno!");
		if(habitaciones.getOrDefault(p, null)!=null) throw new HospitalException("Paciente ya ingresado!"); 
		Habitacion h = libres.first();
		libres.remove(h);
		habitaciones.put(p, h);
	}
	
	public Paciente baja(String nuss) {
		boolean aux = false;
		Paciente p=null;
		Iterator<Paciente> it = habitaciones.keySet().iterator();
		while ( ! aux && it.hasNext()) {
			p = it.next();
			aux = p.getNuss().equals(nuss);
		}
		if(aux) {
			libres.add(habitaciones.get(p));
			habitaciones.remove(p);
		}
		return p;
	}
	
	public Paciente[] seleccion(Criterio c) {
		Paciente[] ans = new Paciente[habitaciones.size()];
		int num= 0;
		for(Entry<Paciente, Habitacion> p:habitaciones.entrySet()) {
			if (c.cumpleCondicion(p.getKey(), p.getValue())) {
				ans[num] = p.getKey();
				num++;
			}
		}
		return Arrays.copyOf(ans, num);
	}
	
	public void leePacientes(String fileName) throws IOException {
		try (Scanner sc = new Scanner(Path.of(fileName))) {
			leePacientes(sc);
		}
	}
	

	private void leePacientes(Scanner sc) {
		while (sc.hasNextLine()) {
			String l = sc.nextLine();
			//System.out.println(l);
			try (Scanner scc = new Scanner(l)) {
				scc.useDelimiter("\\s*[,]\\s*");
				String nombre = scc.next();
				String apellidos = scc.next();
				String nuss = scc.next();
				int ano = scc.nextInt();
				alta(new Paciente(nombre, apellidos, nuss, ano));
			} catch (HospitalException | NoSuchElementException | NumberFormatException | IndexOutOfBoundsException e) {
				// se descarta
			}
		}
	}
	
	
	public void escribePacientes(String f) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter(f)){
			escribePacientes(pw);
		}
	}
	
	private void escribePacientes(PrintWriter pw) {
		for(int i = 0 ;i<plantas;i++) {
			pw.println("Planta:" + i +":");
			for(Paciente p:seleccion(new MismaPlanta(i))) {
				pw.println(p);
			}
		}
	}
	
	public String toString() {
		return "Hospital [nombre=" + nombre + ", pacientes=" + habitaciones + ", libres=" + libres + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
