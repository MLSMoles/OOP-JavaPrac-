import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
	public static void main(String[] arg) {
		
		Set<Integer> c = new HashSet<Integer>();
		c.add(1);
		c.add(2);
		c.add(3);
		c.add(1);
		System.out.println(c);
		System.out.println(c.contains(1));
		System.out.println(c.size());
		Iterator<Integer> iter = c.iterator();
		while(iter.hasNext()){
			Integer i = iter.next();
			System.out.println(i);
		}
	}
}
