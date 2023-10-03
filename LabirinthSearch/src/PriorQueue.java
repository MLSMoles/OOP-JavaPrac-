import java.util.ArrayList;

public class PriorQueue<T> {

	public ArrayList<Nodo> array;

	public PriorQueue() {
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
				Nodo iter = array.get(0);
				while (s.costeG >= iter.costeG && i < size && i != -1) {
					if (s.equals(iter))
						i = -1;
					else if (i+1 < size)
						iter = array.get(i+1);
					if (i != -1)
						i++;

				}
				if (i != -1) {
					if (i == size) {
						array.add(s);

					} else {
						array.add(i, s);
					}
				}
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
