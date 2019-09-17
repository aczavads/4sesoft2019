package aula20190917.mapas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class App {
	
	public static void main(String[] args) {
		List<Venda> vendas = new ArrayList<Venda>();
		vendas.add(new Venda("PR", 177225.11));
		vendas.add(new Venda("SP", 126252.11));
		vendas.add(new Venda("SP", 127253.51));
		vendas.add(new Venda("SP", 127254.12));
		vendas.add(new Venda("RS", 912525.11));
		vendas.add(new Venda("SP", 10.12));
		vendas.add(new Venda("RS", 11151.11));
		
		//exibirVendasPorEstado(vendas);
		exibirTotalPorEstado(vendas);
	}
	private static void exibirVendasPorEstado(List<Venda> vendas) {
		Map<String, List<Venda>> vendasPorEstado = new HashMap<String, List<Venda>>();
		for (Venda v : vendas) {
			String sigla = v.getSiglaDoEstado();
			if (vendasPorEstado.containsKey(sigla)) {
				vendasPorEstado.get(sigla).add(v);
			} else {				
				List<Venda> lista = new ArrayList<Venda>();
				lista.add(v);
				vendasPorEstado.put(sigla, lista);
			}
		}
		
		System.out.println("----------------");
		for (String sigla : vendasPorEstado.keySet()) {
			System.out.println("Vendas realizadas no estado " + sigla);
			
			for (Venda v : vendasPorEstado.get(sigla)) {
				System.out.println("     " + v.getValor());
			}
		}
	}

	private static void exibirTotalPorEstado(List<Venda> vendas) {
		Map<String, Double> totalPorEstado = new HashMap<String, Double>();
		for (Venda v : vendas) {
			if (totalPorEstado.containsKey(v.getSiglaDoEstado())) {
				double total = totalPorEstado.get(v.getSiglaDoEstado());
				totalPorEstado.put(v.getSiglaDoEstado(), total + v.getValor());
			} else {
				totalPorEstado.put(v.getSiglaDoEstado(), v.getValor());
			}			
		}
		System.out.println("=================");
		for (Entry<String, Double> elemento : totalPorEstado.entrySet()) {
			System.out.println(elemento.getKey() + ": " + elemento.getValue());
		}
	}

}
 