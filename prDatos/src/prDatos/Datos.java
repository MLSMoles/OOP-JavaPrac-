package prDatos;
import java.lang.Math;
import java.util.Arrays;

public class Datos {
	private double[] datos;
	private String[] errores;
	private double min, max;

	public Datos(String[] seq, double min, double max) {
		procesarDatos(seq);
		this.min = min;
		this.max = max;
	}

	private void procesarDatos(String[] d) {
		int nd = 0;
		int ne = 0;
		datos = new double[d.length];
		errores = new String[d.length];
		for (int i = 0; i < d.length; i++) {
			try {
				double valor = Double.parseDouble(d[i]);
				datos[nd] = valor;
				ne++;
			} catch (NumberFormatException e) {
				errores[ne] = d[i];
				ne++;
			}
		}
		datos = Arrays.copyOf(datos, nd);
		errores = Arrays.copyOf(errores, ne);

	}

	public double calcMedia() {
		double sum = 0;
		int cont = 0;
		for (int i = 0; i < datos.length; i++) {
			if (datos[i] >= min && datos[i] <= max) {
				sum = sum + datos[i];
				cont++;
			}
		}
		if (cont == 0) {
			throw new DatosException("No hay datos en el rango especificado");
		}
		sum = sum / cont;
		return sum;
	}

	public void setRango(String str) {
		try {
			String[] d = str.split(";");
			min = Integer.parseInt(d[1]);
			max = Integer.parseInt(d[2]);
		} catch (DatosException e) {
			throw new DatosException("Error en los datos al establecer el rango”");
		}
	}

	public double calcDesvTipica() {
		double desv=0;
		double cont = 0;
		double media = this.calcMedia();
		for(int i=0; i < datos.length;i++) {
			if(datos[i]>min && datos[i] < max ) {
				desv +=((datos[i] - media)*(datos[i] - media));
				cont++;
			}
		}
		desv=desv/cont;
		desv=Math.sqrt(desv);
		return desv;
	}

	@Override

	public String toString() {
		String str = "Min:" + min + ", Max:" + max + ",\n";
		str += Arrays.toString(datos) + ",\n";
		str += Arrays.toString(errores) + ",\n";
		try {
			double media = calcMedia();
			str += "Mdedia: " + media;
		} catch (DatosException e) {
			str += "Media: ERROR";
		}
		return str;
	}

	public double[] getDatos() {
		return datos;
	}

	public String[] getErrores() {
		return errores;
	}
}
