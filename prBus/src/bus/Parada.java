package bus;

public class Parada {

	public final static double RADIO_TIERRA = 6378.137;
	private String nombre;
	private double latitud, longitud;

	public Parada(String n, double lat, double lon) {
		if (lat < -90 || lat > 90 || lon > 180 || lon < -180)
			throw new RuntimeException();
		nombre = n;
		latitud = lat;
		longitud = lon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public double distancia(Parada p) {
		
		return distanciaHaversine(this,p);
	}

	
	public String toString() {
		return nombre.toUpperCase() + "(" + latitud + ", " + longitud + ")";
	}
	public static double distanciaHaversine(Parada p1, Parada p2) {
		// Conversión a radianes de latitudes y longitudes de p1 y p2
		double ltRad1 = Math.toRadians(p1.latitud);
		double lgRad1 = Math.toRadians(p1.longitud);
		double ltRad2 = Math.toRadians(p2.latitud);
		double lgRad2 = Math.toRadians(p2.longitud);
		// Se utiliza la fórmula de Havershine para aproximar la distancia
		double distancia = RADIO_TIERRA * Math.acos(
				Math.cos(ltRad1) * Math.cos(ltRad2) * Math.cos(lgRad2 - lgRad1) + Math.sin(ltRad1) * Math.sin(ltRad2));
		return distancia;
	}
}
