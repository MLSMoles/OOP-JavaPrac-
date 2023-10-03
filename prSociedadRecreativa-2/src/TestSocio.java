import java.util.HashSet;
import java.util.Set;

import sociedad.Socio;

public class TestSocio {

	public static void main(String args[]) {
		Set<String> sint = new HashSet<String>();
		sint.add("Senderismo");
		sint.add("Escalada");
		Socio m1 = new Socio("Layton Kor", sint, 24);
		Socio m2 = new Socio("layton kor", new HashSet<String>(), 24);
		System.out.println(m1);
		System.out.println(m2);
		if (m1.equals(m2)) {
			System.out.println("Son el mismo socio.");
		} else {
			System.out.println("Son socios distintos.");
		}
	}
}
