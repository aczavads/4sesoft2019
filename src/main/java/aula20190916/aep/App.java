package aula20190916.aep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		conceitos.add(new Conceito(3, new Nota(9.8), josias, bancoII));
		conceitos.add(new Conceito(2, new Nota(9.5), ana,programacaoOO));		
		conceitos.add(new Conceito(5, new Nota(6.0), jorge, bancoII));
		conceitos.add(new Conceito(4, new Nota(10.0), josias, programacaoOO));
		conceitos.add(new Conceito(6, new Nota(2.5), jorge, programacaoOO));
		
		System.out.println("A nota média é: " + calcularNotaMédia(conceitos));
		System.out.println("O melhor aluno(a) é: " + identificarMelhorAluno(conceitos).getNome());
		
		
		
		System.out.println("Foi.");		
	}
	private static Aluno identificarMelhorAluno(List<Conceito> conceitos) {
		Map<Aluno, Double> mapaDeTotalDaNotaPorAluno = new HashMap<Aluno, Double>();
		for (Conceito conceito : conceitos) {
			Aluno aluno = conceito.getAluno();
			double nota = conceito.getNota().getValor();
			if (mapaDeTotalDaNotaPorAluno.containsKey(aluno)) {
				double total = mapaDeTotalDaNotaPorAluno.get(aluno);
				mapaDeTotalDaNotaPorAluno.put(aluno, total + nota);
			} else {
				mapaDeTotalDaNotaPorAluno.put(aluno, nota);
			}
		}
		
		Double maior = 0.00;
		Aluno melhor = null;
		for (Entry<Aluno, Double> elemento : mapaDeTotalDaNotaPorAluno.entrySet()) {
			if (elemento.getValue() > maior) {
				maior = elemento.getValue();
				melhor = elemento.getKey();
			}
		}
		
		return melhor;
	}
	
	/*	
	private static Aluno identificarMelhorAluno(List<Conceito> conceitos) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<Double> totalDasNotas = new ArrayList<Double>();
		
		for (Conceito conceito: conceitos) {
			Aluno aluno = conceito.getAluno();
			int posicaoAluno = alunos.indexOf(aluno);
			if (posicaoAluno == -1) {
				alunos.add(aluno);
				totalDasNotas.add(conceito.getNota().getValor());
			} else {
				double nota = totalDasNotas.get(posicaoAluno);
				totalDasNotas.remove(posicaoAluno);
				nota += conceito.getNota().getValor();
				totalDasNotas.add(posicaoAluno, nota);
			}
		}		
		return recuperarAlunoComMaiorTotalDeNotas(alunos, totalDasNotas);
	}
	*/
	
	private static Aluno recuperarAlunoComMaiorTotalDeNotas(List<Aluno> alunos, List<Double> totalDasNotas) {
		Aluno melhor;
		double maiorNota = -1;
		int posicaoDaMaior = -1;
		for (int i=0; i<totalDasNotas.size();i++) {
			if (totalDasNotas.get(i) > maiorNota) {
				maiorNota = totalDasNotas.get(i);
				posicaoDaMaior = i;
			}
		}		
		melhor = alunos.get(posicaoDaMaior);
		return melhor;
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
