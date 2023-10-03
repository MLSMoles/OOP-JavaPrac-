package cuentapalabras;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
public class ContadorPalabras {

	private ArrayList<PalabraEnTexto> list;
	
	public ContadorPalabras() {
		list = new ArrayList<>();
	}
	
	private int esta(String pal) {
		int i =0;
		PalabraEnTexto p = new PalabraEnTexto(pal);
		while(i<list.size() && !list.get(i).equals(p)) i++;
		return (i==list.size()) ? -1:i;
	}
	
	
	protected void incluye(String pal) {
		if(pal != null) {
		int i = this.esta(pal);
		if(i!=-1) list.get(i).incrementa();
		else list.add(new PalabraEnTexto(pal));
		}
	}
	
	private void incluyeTodas(String linea, String del) {
		try(Scanner sc = new Scanner(linea)){
			sc.useDelimiter(del);
			while(sc.hasNext()) {
				incluye(sc.next());
			}
		}
	}
	
	public void incluyeTodas(String[] texto, String del){
		for(String ss:texto) {
			incluyeTodas(ss,del);
		}
	}
	
	public void incluyeTodasFichero(String nomFich, String del) {
		try(Scanner sc = new Scanner(new File(nomFich))){
			 	while(sc.hasNextLine()) {
			 		this.incluyeTodas(sc.nextLine(),del);
			 	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PalabraEnTexto encuentra(String pal){
		int i = this.esta(pal);
		if(i==-1)throw new NoSuchElementException();
		return list.get(i);
		
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(" - ","[","]");
		for(PalabraEnTexto pb:list) {
			sj.add(pb.toString());
		}
		return sj.toString();
	}
	
	public void presentaPalabras(String fichero) {
		try(PrintWriter pw = new PrintWriter(fichero) ){
			presentaPalabras(pw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}
	}
	
	public void presentaPalabras(PrintWriter pw) {
		for(PalabraEnTexto pb: list) {
			pw.println(pb);
		}
	}

}
