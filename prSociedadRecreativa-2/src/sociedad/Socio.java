package sociedad;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Socio implements Comparable<Socio> {

	private String name;
	private Set<String> interests;
	private int ident;

	public Socio(String n, Set<String> i, int id){
		if (n == null || n.equals("") || (id <= 0)) {
			throw new SociedadException("Incorrect values to create new member.");
		}
		name = n;
		interests = new HashSet<>();
		for (String interest : i) {
			interests.add(interest.toLowerCase());
		}
		ident = id;
	}

	public String getNombre() {
		return name;
	}

	public Set<String> getIntereses() {
		return interests;
	}

	public int getIdentificador() {
		return ident;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ident, name.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = false;
		if (this == obj) {
			eq = true;
		} else if (!(obj instanceof Socio)) {
			eq = false;
		} else {
			Socio other = (Socio) obj;
			eq = ident == other.ident && name.equalsIgnoreCase(other.name);
		}

		return eq;
	}

	@Override
	public String toString() {
		return "[" + name + ", " + interests.toString() + ", " +ident + "]";
	}


	@Override
	public int compareTo(Socio m) {
		int ct = name.compareToIgnoreCase(m.getNombre());
		if (ct == 0) {
			ct = Integer.compare(ident, m.ident);
		}
		return ct;
	}

}
