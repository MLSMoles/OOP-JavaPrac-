package bus;

public class LineaBusSentido extends LineaBus{
	private boolean sentidoInvertido;
	
	public LineaBusSentido(String s) {
		super(s);
		sentidoInvertido = true;
	}
	
	public void cambiarSentido() {
		sentidoInvertido = !sentidoInvertido;
	}
	public Parada inicio() {
		if(sentidoInvertido) return super.inicio();
		return super.fin();
	}
	public Parada fin() {
		if(!sentidoInvertido) return super.inicio();
		return super.fin();
	}
}
