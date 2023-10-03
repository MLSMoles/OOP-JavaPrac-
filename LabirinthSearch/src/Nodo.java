

public class Nodo {

	public int cordX, cordY;
	public int costeP;
	public int costeH;
	public int costeG;
	public Nodo padre;

	/**
	 *  Constructor of a Node, first to arguments are assigned as X and Y position , third argument is reference to a father node.
	 * @autor Ruslan Khafizov
	 */
	public Nodo(int cordX, int cordY,Nodo padre) {
		this.cordX = cordX;
		this.cordY = cordY;
		this.padre = padre;
	}
	
	/**
	 *  Method t oset cost for a node, first argument is a cost of a step and second is a cost calculated by heuristic algorithm.
	 * @autor Ruslan Khafizov
	 */
	public void setCostes(int P,int H) {
		costeP = P;
		costeH = H;
		costeG = P+H;
	}
	/**
	 *  Method that checks whether 2 nodes are same or not.
	 * @autor Ruslan Khafizov
	 */
	public boolean equals(Nodo s) {
		return (s.cordX == this.cordX && s.cordY == this.cordY);
	}
	/**
	 *  Return String format of a Node, first value is x coordinate, and second is y, the last one is total cost.
	 * @autor Ruslan Khafizov
	 */
	public String toString() {
		return Integer.toString(cordY) + "/" + Integer.toString(cordX) +"/" + Integer.toString(costeG);
	}
}
