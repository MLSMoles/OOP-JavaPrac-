import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Laberinto {
	static final int CASE = 0; // 0 Usa ■ y . en vez de * y espacio libre, 1-modo normal
	static final int HEUR = 0; // 0-euclid, 1-manhat, 2-implementación de h=0, usando euclideo para caluclo del coste
	public int dimensionX, dimensionY;
	public char[][] matriz;
	// probabilidad es alrededor de 33%
	public int iniX, iniY, objX, objY;
	public String seed = "";
	public String seedV = "";
	ArrayList<Nodo> solucion;

	// Constructor

	/**
	 * Construct a laberynth with dimension X by Y, X being first value introduced
	 * and Y being second value introduced.
	 * 
	 * @autor Ruslan Khafizov
	 */
	public Laberinto(int dimensionY, int dimensionX) {
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		matriz = new char[this.dimensionX][this.dimensionY];
		solucion = new ArrayList<Nodo>();
	}

	// A-star algorithm implementation
	/**
	 * Paints a solution on a laberynth matrix. The solution must be given as an
	 * array of nodes.
	 * 
	 * @autor Ruslan Khafizov
	 */
	public void pintarSolucion(ArrayList<Nodo> n) {
		for (int i = 1; i < n.size() - 1; i++) {
			int y = n.get(i).cordY;
			int x = n.get(i).cordX;
			matriz[x][y] = '+';
		}
	}

	/**
	 * Solve an laberynth for a given intance of a problem.
	 * 
	 * @autor Ruslan Khafizov
	 */
	@SuppressWarnings("unused")
	public void solveLaberynth() throws Exception {
		if (HEUR != 2) {
			Nodo s = new Nodo(iniX, iniY, null);
			s.setCostes(0, distance(s));
			PriorQueue<Nodo> cerrados = new PriorQueue();
			PriorQueue<Nodo> abiertos = new PriorQueue();
			cerrados.enqueu(s);
			abiertos = adj(s, cerrados, abiertos);
			boolean encontrado = false;
			while (!encontrado && !abiertos.empty()) {
				Nodo s1 = abiertos.dequeue();
				cerrados.enqueu(s1);
				encontrado = found(s1);
				if (encontrado) {
					CreateSolution(s1);
				} else {
					abiertos = adj(s1, cerrados, abiertos);
				}
			}
			if (abiertos.empty()) {
				throw new Exception("No hay solución para esta instancia del problema.");
			}
		} else {
			Nodo s = new Nodo(iniX, iniY, null);
			s.setCostes(0, distance(s));
			NQueue<Nodo> cerrados = new NQueue<>();
			NQueue<Nodo> abiertos = new NQueue<>();
			cerrados.enqueu(s);
			abiertos = adj(s, cerrados, abiertos);
			boolean encontrado = false;
			while (!encontrado && !abiertos.empty()) {
				Nodo s1 = abiertos.dequeue();
				cerrados.enqueu(s1);
				encontrado = found(s1);
				if (encontrado) {
					CreateSolution(s1);
				} else {
					abiertos = adj(s1, cerrados, abiertos);
				}
			}
			if (abiertos.empty()) {
				throw new Exception("No hay solución para esta instancia del problema.");
			}
		}
	}
	
	private NQueue<Nodo> adj(Nodo s, NQueue<Nodo> cerrados, NQueue<Nodo> abiertos) {
		int x = s.cordX;
		int y = s.cordY;
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		if (x - 1 >= 0 && matriz[x - 1][y] != '*' && matriz[x - 1][y] != '■') {
			Nodo s1 = new Nodo(x - 1, y, s);
			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (y - 1 >= 0 && matriz[x][y - 1] != '*' && matriz[x][y - 1] != '■') {
			Nodo s1 = new Nodo(x, y - 1, s);
			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (x + 1 < dimX && matriz[x + 1][y] != '*' && matriz[x + 1][y] != '■') {
			Nodo s1 = new Nodo(x + 1, y, s);

			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (y + 1 < dimY && matriz[x][y + 1] != '*' && matriz[x][y + 1] != '■') {
			Nodo s1 = new Nodo(x, y + 1, s);

			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		return abiertos;
	}

	/**
	 * Private method to calculate distance using heuristic approach.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private int distance(Nodo n) {
		double d = 0;
		switch (HEUR) {
		case (0):
			d = (Math.pow(objX - n.cordX, 2) + Math.pow(objY - n.cordY, 2));
			d = Math.sqrt(d);
			break;
		case (1):
			d = (Math.abs(objX - n.cordX) + Math.abs(objY - n.cordY));
			break;
		default: 
			d = (Math.pow(objX - n.cordX, 2) + Math.pow(objY - n.cordY, 2));
			d = Math.sqrt(d);
		}
		return (int) d;
	}

	/**
	 * Private method that checks whether the solution was found.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private boolean found(Nodo s) {
		return (s.cordX == objX && s.cordY == objY);
	}

	/**
	 * Private method that returns a new priority-queue of open nodes that are
	 * ordered by global cost.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private PriorQueue<Nodo> adj(Nodo s, PriorQueue<Nodo> cerrados, PriorQueue<Nodo> abiertos) {
		int x = s.cordX;
		int y = s.cordY;
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		if (x - 1 >= 0 && matriz[x - 1][y] != '*' && matriz[x - 1][y] != '■') {
			Nodo s1 = new Nodo(x - 1, y, s);
			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (y - 1 >= 0 && matriz[x][y - 1] != '*' && matriz[x][y - 1] != '■') {
			Nodo s1 = new Nodo(x, y - 1, s);
			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (x + 1 < dimX && matriz[x + 1][y] != '*' && matriz[x + 1][y] != '■') {
			Nodo s1 = new Nodo(x + 1, y, s);

			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		if (y + 1 < dimY && matriz[x][y + 1] != '*' && matriz[x][y + 1] != '■') {
			Nodo s1 = new Nodo(x, y + 1, s);

			if (!cerrados.contains(s1)) {
				s1.setCostes(s.costeP + 1, distance(s1));
				abiertos.enqueu(s1);
			}
		}
		return abiertos;
	}

	// Solution array creation

	/**
	 * Private method that returns an array nodes that construct solution, starts
	 * with the Node I and ends with Node G, 'I' being initial state and 'G' final
	 * state.
	 * 
	 * @autor Ruslan Khafizov
	 */

	public ArrayList<Nodo> solucion() {
		return solucion;
	}

	/**
	 * Private method that swaps elements mirrorly.
	 * 
	 * @autor Ruslan Khafizov
	 */

	private ArrayList<Nodo> Swap() {
		int size = solucion.size() - 1;
		if (size == 2) {
			Nodo aux = solucion.get(0);
			solucion.remove(0);
			solucion.add(1, aux);
		} else {
			for (int i = 0; i < (size + 1) / 2; i++) {
				Nodo aux = solucion.get(i);
				solucion.set(i, solucion.get(size - i));
				solucion.set(size - i, aux);
			}
		}
		return solucion;
	}

	/**
	 * Private method that creates array solution.
	 * 
	 * @autor Ruslan Khafizov
	 */

	private void CreateSolution(Nodo s) {
		Nodo iter = s.padre;
		solucion.add(s);
		while (iter.padre != null) {
			solucion.add(iter);
			iter = iter.padre;
		}
		solucion.add(iter);
		solucion = Swap();
	}
	// Laberynth generation

	/**
	 * Creates a laberynth with randomly generated seed.
	 * 
	 * @autor Ruslan Khafizov
	 */

	public void generarLaberinto() throws Exception {
		seed = SeedGenerator();
		BeginEnd();
		FillLaberynth();
	}

	/**
	 * Creates a laberynth using given seed in an argument.
	 * 
	 * @autor Ruslan Khafizov
	 */

	public void generarLaberinto(String seed) throws Exception {
		this.seedV = seed;
		if (seed.length() != 32)
			this.seed = getMd5(seed);
		else
			this.seed = seed;
		BeginEnd();
		FillLaberynth();

	}

	/**
	 * Private method that fills with matrix of a laberynth with randomly generated
	 * ways, using seed.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private void FillLaberynth() {
		int i = 0;
		char c;
		int q = this.dimensionX * this.dimensionY;
		while (q > seed.length()) {
			seed += getMd5(seed);
			seed = rearrangeString(seed);
		}
		for (int l = 0; l < matriz.length; l++) {
			for (int k = 0; k < matriz[0].length; k++) {
				c = seed.charAt((i) % seed.length());
				i++;
				int p = (c) % 3;
				if (matriz[l][k] != 'I' && matriz[l][k] != 'G') {
					switch (CASE) {
					case (0):
						matriz[l][k] = (p == 0) ? '■' : '.';
						break;
					case (1):
						matriz[l][k] = (p == 0) ? '*' : ' ';
						break;
					}
				}
			}
		}
	}

	/**
	 * Private method that rearranges a seed in order to randomize even more
	 * laberynth.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private static String rearrangeString(String seed) {
		int len = seed.length();
		len = len / 3;
		char[] SeedChar = seed.toCharArray();
		for (int i = 0; i < len * 2; i++) {
			int len2 = (2 * len) % seed.length();
			char c1 = SeedChar[len];
			char c2 = SeedChar[len2];
			SeedChar[len] = c2;
			SeedChar[len] = c1;
		}
		seed = String.valueOf(SeedChar);
		return seed;
	}

	/**
	 * Private method that assigns Begining and End points. Using seed given.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private void BeginEnd() throws Exception {
		char c1 = seed.charAt(0);
		char c2 = seed.charAt(1);
		int x = c1 + c2;
		x = x % dimensionX;
		iniX = x;

		c1 = seed.charAt(2);
		c2 = seed.charAt(3);
		int y = c1 + c2;
		y = y % dimensionY;
		iniY = y;
		matriz[x][y] = 'I';

		c1 = seed.charAt(4);
		c2 = seed.charAt(5);
		x = c1 + c2;
		x = x % dimensionX;
		objX = x;

		c1 = seed.charAt(6);
		c2 = seed.charAt(7);
		y = c1 + c2;
		y = y % dimensionY;
		objY = y;
		matriz[x][y] = 'G';
		if (iniX == objX && iniY == objY)
			throw new Exception(
					"In created seed Initial and Object states are the same. Try again or try a different seed.");

	}

	/**
	 * Private method that generates a seed when it is not given by a user.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private String SeedGenerator() throws Exception {
		long g = getSystemUptime();
		Random r = new Random();
		g = r.nextLong();
		seed = getMd5(Long.toString(g));
		for (int i = 0; i < seed.length(); i++) {
			seedV += Character.toString(seed.charAt(i));
		}
		return seed;
	}

	/**
	 * Private method that Hash a given String using Md5 method, it is used for seed
	 * creation.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private String getMd5(String input) {
		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Private method that returns System uptime, it is used in seed creation when
	 * not given one.
	 * 
	 * @autor Ruslan Khafizov
	 */
	private long getSystemUptime() throws Exception {
		long uptime = -1;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("mac")) {
			Process uptimeProc = Runtime.getRuntime().exec("uptime");
			BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
			String line = in.readLine();
			if (line != null) {
				Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
				Matcher matcher = parse.matcher(line);
				if (matcher.find()) {
					String _days = matcher.group(2);
					String _hours = matcher.group(3);
					String _minutes = matcher.group(4);
					int days = _days != null ? Integer.parseInt(_days) : 0;
					int hours = _hours != null ? Integer.parseInt(_hours) : 0;
					int minutes = _minutes != null ? Integer.parseInt(_minutes) : 0;
					uptime = (minutes * 60000) + (hours * 60000 * 60) + (days * 6000 * 60 * 24);
				}
			}
		}
		return uptime;
	}

	// Additional methods
	public String Seed() {
		return seedV;
	}

	/**
	 * Paints laberynth matrix in a console.
	 * 
	 * @autor Ruslan Khafizov
	 */
	public void mostrarLaberinto() {
		for (int i = 0; i < this.dimensionX; i++) {
			for (int k = 0; k < this.dimensionY; k++) {
				switch (CASE) {
				case (0):
					System.out.print(matriz[i][k] + " ");
					break;
				case (1):
					System.out.print(matriz[i][k]);
				}
			}
			System.out.println();
		}
	}

	/**
	 * Clears whole laberynth of any obstacles, basically creates a blank matrix.
	 * 
	 * @autor Ruslan Khafizov
	 */
	public void clear() {
		matriz = new char[this.dimensionX][this.dimensionY];
	}

}
