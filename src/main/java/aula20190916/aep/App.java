package aula20190916.aep;

import java.util.ArrayList;
import java.util.List;

public class App {

	/*
	 * Conceito(id: String, nota: Nota{required}, aluno: Aluno{required},
	 * disciplina: Disciplina) Nota deve possuir valor entre 0 e 10.
	 */
	public static void main(String[] args) {
		List<Conceito> conceitos = new ArrayList();
		
		Aluno ana = new Aluno("5555-55", "Ana Lúcia");
		Aluno josias = new Aluno("1234-11", "Josias");
		Aluno jorge = new Aluno("5541-11", "Jorge");
		
		Disciplina bancoII = new Disciplina("Banco de dados II");
		Disciplina programacaoOO = new Disciplina("Programação Orientada a Objetos");
		
		conceitos.add(new Conceito(1, new Nota(8.5), ana,bancoII));
		conceitos.add(new Conceito(2, new Nota(9.5), ana,programacaoOO));		
		conceitos.add(new Conceito(3, new Nota(1.8), josias, bancoII));
		conceitos.add(new Conceito(4, new Nota(6.5), josias, programacaoOO));
		conceitos.add(new Conceito(5, new Nota(6.0), jorge, bancoII));
		conceitos.add(new Conceito(6, new Nota(2.5), jorge, programacaoOO));
		
		System.out.println("A nota média é: " + calcularNotaMédia(conceitos));
		
		
		
		System.out.println("Foi.");		
	}
	private static double calcularNotaMédia(List<Conceito> conceitos) {
		double média = 0.00;
		
		for (Conceito conceito : conceitos) {
			média += conceito.getNota().getValor();
		}
		média = média / conceitos.size();
		return média;
	}

}
