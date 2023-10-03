package Tema6;

public class Ej6 {

	private static boolean hamilton(boolean[][] graph, int[] visited, int LastVisited, int V) {
		boolean ans = false;
		int counter = 0;
		ans = LastVisited == graph.length;
		while (!ans && counter < graph.length) {
			if (visit(visited, counter) && graph[V][counter] && V != counter) { 
				visited[LastVisited] = V;
				ans = hamilton(graph, visited, LastVisited+1, counter);
			}counter++;
		}
		
		
		return ans;
	}

	private static boolean visit(int[] array, int v) {
		int aux = 0;
		boolean ans = true;
		while (aux < array.length && ans) {
			ans = array[aux] != v;
		}
		return ans;
	}

	private static boolean euleriano(boolean[][] graph, int V,int counter,int[] sol, int idx) {
		boolean ans = false;
		if(counter == Math.pow(graph.length,2)) return true;
		int aux = 0;
		while (!ans && aux < graph.length) {
			if (aux != V && graph[V][aux]) { 
				graph[V][aux] = false;
				graph[aux][V] = false;
				counter +=2;
				sol[idx] = V;
				ans = euleriano(graph,aux,counter,sol,idx++);
				if(!ans) {
					counter-=2;
					graph[V][aux] = true;
					graph[aux][V] = true;
					sol[idx] = -1;
				}
			}aux++;
		}
		return ans;
	}

	// calcula numero de enlaces que faltan entre vertices para que sea completo
	private static int FalseCounter(boolean[][] graph) {
		int counter = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				counter += (graph[i][j]) ? 0 : 1;
			}
		}
		return counter;
	}
}
