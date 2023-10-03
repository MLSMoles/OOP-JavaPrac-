import java.util.ArrayList;

public class NQueue<T> {
	
	public ArrayList<Nodo> array;

	public NQueue() {
		array = new ArrayList<Nodo>();
	}

	public int size() {
		return array.size();
	}

	public Nodo first() {
		return array.get(0);
	}

	public Nodo last() {
		return array.get(array.size());
	}

	public boolean empty() {
		return array.size() == 0;
	}

	public Nodo getNode(int index) {
		return array.get(index);
	}

	public void enqueu(Nodo s) {
		int size = array.size();
		int i = 0;
		if (size == 0)
			array.add(s);
		else {
			if (!array.contains(s)) {
				array.add(s);
			}
		}
	}

	public Nodo dequeue() {
		Nodo s1 = array.get(0);
		Nodo s = new Nodo(s1.cordX,s1.cordY,s1.padre);
		s.setCostes(s1.costeP, s1.costeH);
		array.remove(0);
		return s;
	}

	public String toString() {
		String ss = "";
		for (int i = 0; i < array.size(); i++) {
			ss += array.get(i).toString();
			ss += "--";
		}
		return ss;
	}

	public boolean contains(Nodo s) {
		int size = array.size();
		int i = 0;
		boolean ans = false;
		while (!ans && i < size) {
			ans = (s.equals(array.get(i))) ? true : false;
			i++;
		}
		return ans;
	}

}
