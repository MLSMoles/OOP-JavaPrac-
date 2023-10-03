package bus;

/**
 * La clase Parada mantiene informaci�n sobre paradas de autob�s, 
 * incluyendo su nombre (de tipo String) y su latitud y longitud para determinar 
 * su posici�n GPS (ambas de tipo double). 
 * 
 * @author Ruslan Khafizov
 *
 */
public class Parada {
	/**
	 * Constante que representa el radio de la Tierra, y que se utilizar� para 
	 * calcular la distancia geod�sica entre dos puntos.
	 */
	public final static double RADIO_TIERRA = 6378.137;
	
	/**
	 * Nombre de la parada
	 */
	private String nombre;
	
	/**
	 * Variable de instancia para representar la latitud de la parada
	 */
	private double latitud;
	
	/**
	 * Variable de instancia para representar la longitud de la pareda
	 */
	private double longitud;
	
	/**
	 * Constructor que crea objetos de la clase Parada, a partir de su nombre, latitud y longitud.
	 * @param n	Nombre de la parada
	 * @param lt	Latitud
	 * @param lg	Longitud
	 */
	public Parada(String n, double lt, double lg) {
		if(lt<-90| lt>90|lg<-180|lg>180) throw new BusException("Error en logitud o latitud!");
		nombre = n;
		longitud = lg;
		latitud = lt;
	}
	
	/**
	 * Devuelve el nombre de la parada
	 * @return	Nombre de la parada
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Cambia el nombre de la parada
	 * @param nuevoNombre
	 */
	public void setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}
	
	/**
	 * Devuelve la longitud de la posici�n de la parada
	 * @return	Longitud de la parada
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Devuelve la latitud de la posici�n de la parada
	 * @return	Latitud de la parada
	 */
	public double  getLatitud() {
		return latitud;
	}
	
	/**
	 * Devuelve la distancia entre la parada que recibe el mensaje y la que se
	 * pasa como argumento
	 * @param par	Parada
	 * @return			Distancia entre las paradas
	 */
	public double distancia(Parada par) {
		// Devuelve la distancia con la parada par, con tres decimales
		return Math.ceil(distanciaGeodesica(this,par)*1000) / 1000; 
	}
	
	/**
	 * M�todo est�tico que calcula la distancia entre dos paradas aplicando 
	 * la f�rmula de la distancia geod�isca.
	 * @param p1		Parada
	 * @param p2		Parada
	 * @return			Distancia entre dos paradas
	 */
	public static double distanciaGeodesica(Parada p1, Parada p2) {
		double ltRad1 = Math.toRadians(p1.latitud);
		double lgRad1 = Math.toRadians(p1.longitud);
		double ltRad2 = Math.toRadians(p2.latitud);
		double lgRad2 = Math.toRadians(p2.longitud);
		// Se utiliza la f�rmula de Haversine para aproximar la distancia
		double distancia = RADIO_TIERRA * Math.acos(
				Math.cos(ltRad1) * Math.cos(ltRad2) * Math.cos(lgRad2 - lgRad1) + Math.sin(ltRad1) * Math.sin(ltRad2));
		return distancia;
	}

	public boolean equals(Object o) {
		boolean ans = o instanceof Parada;
		if(ans) {
			Parada p = (Parada) o;
			ans = p.nombre.equalsIgnoreCase(this.nombre) && p.latitud == this.latitud && p.longitud == this.longitud;
		}
		return ans;
	}
	
	/**
	 * Representaci�n textual de una parada de la forma:
	 * 		NOMBRE DE LA PARADA(latitud, longitud);
	 */
	public String toString() {
		return nombre.toUpperCase() + "(" + latitud + "," + longitud + ")";
	}
}