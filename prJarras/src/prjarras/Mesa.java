package prjarras;

public class Mesa {
	private Jarra jarra1;
	private Jarra jarra2;
	public Mesa(Jarra j1,Jarra j2) {
		if(j1==j2) throw new RuntimeException();
		jarra1=j1;
		jarra2=j2;
	}
	
	public Mesa(int c1,int c2) {
		jarra1 = new Jarra(c1);
		jarra2 = new Jarra(c2);
	}
	
	public int capacidad(int j) {
		if(j!=1 && j!=2) throw new RuntimeException();
		if(j==1) return jarra1.capacidad();
		return jarra2.capacidad();
	}
	
	public int contenido(int j) {
		if(j!=1 && j!= 2) throw new RuntimeException();
		if(j == 1) return jarra1.contenido();
		return jarra2.contenido();
	}
	
	public void llena(int j) {
		if(j!=1 && j!= 2) throw new RuntimeException();
		if(j == 1)  jarra1.llena();
		else jarra2.llena();
	}
	
	public void vacia(int j) {
		if(j!=1 && j!= 2) throw new RuntimeException();
		if(j == 1)  jarra1.vacia();
		else jarra2.vacia();
	}
	
	public void llenaDesde(int j) {
		if(j!=1 && j!= 2) throw new RuntimeException();
		if(j == 1)  jarra2.llenaDesde(jarra1);
		else jarra1.llenaDesde(jarra2);
	}
	
	public String toString() {
		return "M("+ jarra1.toString() + ", " + jarra2.toString() + ")";
	}
	
}
