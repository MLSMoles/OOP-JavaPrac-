package hospital;
import java.util.*;
public class HospitalPlus extends Hospital {
	
	public HospitalPlus(String s,int i1,int i2) throws HospitalException {
		super(s,i1,i2);
	}
	
	
	public SortedMap<Integer, SortedSet<Paciente>> pacientesPorAno(){
		SortedMap<Integer, SortedSet<Paciente>> ans = new TreeMap<>();
		for(Paciente p:habitaciones.keySet()) {
			int anno = p.getAnoDeNacimiento();
			SortedSet<Paciente> aux = ans.getOrDefault(anno,new TreeSet<>());
			aux.add(p);
			ans.put(anno, aux);
		}
		return ans;
	}
	
	public SortedMap<Integer, SortedSet<Paciente>> pacientesPorAno0 () {
		SortedMap<Integer, SortedSet<Paciente>> cs = new TreeMap<>();
		for (Paciente p : habitaciones.keySet()) {
			SortedSet<Paciente> set = cs.get(p.getAnoDeNacimiento());
			if (set == null) {
				set = new TreeSet<>();
				cs.put(p.getAnoDeNacimiento(), set);
			}
			set.add(p);
		}
		return cs;
	}


	public SortedMap<Integer, Integer> numeroDePacientesPorAno() {
		SortedMap<Integer, SortedSet<Paciente>> cs = pacientesPorAno();
		SortedMap<Integer, Integer> ns = new TreeMap<>();
		for (Integer ano : cs.keySet()) {
			ns.put(ano, cs.get(ano).size());
		}
		return ns;
	}

}
