package sociedad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Sociedad {
	private Map<String, Set<Socio>> membersInActivities;
	private Set<Socio> membersNotInActivities;

	public Sociedad() {
		membersInActivities = new HashMap<>();
		membersNotInActivities = new HashSet<>();
	}

	public void leerDeFichero(String filePath) {
		try {
			List<String> linesInFile = Files.readAllLines(Path.of(filePath));
			for (String line : linesInFile) {
				try {
					String[] lineSp = line.split("%");
					if (lineSp.length == 3) {
						String name = lineSp[0];
						Set<String> sInt = new HashSet<>();
						try (Scanner intSc = new Scanner(lineSp[1])) {
							intSc.useDelimiter(",");
							while (intSc.hasNext()) {
								sInt.add(intSc.next());
							}
						}
						int id = Integer.parseInt(lineSp[2]);
						nuevoSocio(new Socio(name, sInt, id));
					} 
				}catch(Exception e) {
					// ignore line and keep processing the rest of the file
				}
			}
		} catch (IOException e) {
			throw new SociedadException("IOException " + e.getMessage() + 
					" al leer del fichero " + filePath);
		}
	}

	public void nuevoSocio(Socio m){
		Set<String> activities = membersInActivities.keySet();
		Iterator<String> iterAct = activities.iterator();
		boolean found = false;
		while(!found && iterAct.hasNext()) {
			found = membersInActivities.get(iterAct.next()).contains(m);
		}
		if (!found && !membersNotInActivities.contains(m)) {
			membersNotInActivities.add(m);
		}
	}

	/**
	 * 
	 * Eliminado del examen porque era demasiado largo
	 * 
	 */
//	public int inscritos() {
//		int enr = 0;
//		for (Set<Socio> sm : membersInActivities.values()) {
//			enr += sm.size();
//		}
//		return enr;
//	}

	public Set<Socio> inscritos(String activity) {
		return membersInActivities.get(activity.toLowerCase());
	}

	/**
	 * 
	 * Eliminado del examen porque era demasiado largo
	 * 
	 */
//	public Set<Socio> sociosInteresados(String interest){
//		Set<Socio>interestedInActivity = new HashSet<Socio>();
//		for (Socio m : membersNotInActivities) {
//			if (m.getIntereses().contains(interest.toLowerCase())) {
//				interestedInActivity.add(m);
//			}
//		}
//		return interestedInActivity;
//	}

	protected Socio buscarSocioEnConjunto(Socio m, Set<Socio> sm) {
		boolean found = false;
		Socio presentMember = null;
		if(sm != null) {
			Iterator<Socio> itMem = sm.iterator();
			while(!found && itMem.hasNext()) {
				presentMember = itMem.next();
				if(presentMember.equals(m)) {
					found = true;
				}
			}
		}
		return found?presentMember:null;
	}

	public void inscribir (String nombre, int id, String activity) {
		Socio m = new Socio(nombre, new HashSet<>(), id);
		if (membersNotInActivities.contains(m)) {
			Socio miembroBase = buscarSocioEnConjunto(m, membersNotInActivities);
			membersNotInActivities.remove(miembroBase);
			Set<Socio> sm = membersInActivities.get(activity.toLowerCase());
			if (sm == null) {
				sm = new HashSet<Socio>();
				membersInActivities.put(activity.toLowerCase(), sm);
			}
			sm.add(miembroBase);
		}
	}

	/**
	 * 
	 * Eliminado del examen porque era demasiado largo
	 * 
	 */
//	public void anularInscripcion (String nombre, int id, String activity) {
//		Socio m = new Socio(nombre, new HashSet<>(), id);
//		Set<Socio> sm = membersInActivities.get(activity.toLowerCase());
//		if (sm != null && sm.contains(m)) {
//			Socio localMember = buscarSocioEnConjunto(m, sm);
//			sm.remove(localMember);
//			membersNotInActivities.add(localMember);
//		} 
//	}

	public void guardarSocios(String filePath) {
		try (PrintWriter pw = new PrintWriter(filePath)) {
			guardarSocios(pw);
		} catch (FileNotFoundException e) {
			throw new SociedadException("No encontrado fichero " + filePath + ".");
		}
	}

	public void guardarSocios(PrintWriter pw) {
		for (Socio m : membersNotInActivities) {
			pw.println(m.toString());
		}
		pw.flush();
	}

}
