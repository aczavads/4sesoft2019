package aula20190827.coleções_da_linguagem;

import java.util.ArrayList;
import java.util.Date;

public class App {
	
	public static void main(String[] args) {
		
		ArrayList lista = new ArrayList();
		lista.add(10);
		lista.add(new Date());
		lista.add("Fulano");
		lista.add('x');
		lista.add(125.33);
		lista.add(false);
		lista.add(false);
		lista.add(true);
		lista.add(100 == 100);
		
		for (int contador=0; contador < lista.size(); contador++) {
			System.out.println(contador + " ==> " + lista.get(contador));
		}
		//Exemplo de template parameter: forma de parametrizar uma classe (deve ser previsto na implementação da classe)
		ArrayList<Integer> listaDeNomes = new ArrayList<Integer>();
		//listaDeNomes.add("Fulano");
		listaDeNomes.add(110);
		
	}

}
