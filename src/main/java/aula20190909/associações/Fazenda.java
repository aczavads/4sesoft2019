package aula20190909.associações;

import java.util.ArrayList;
import java.util.List;

public class Fazenda {
	private final String nome;
	private final double areaEmHectares;
	private double valorDeMercado;
	private List<Cultivar> cultivares = new ArrayList<Cultivar>();
	private List<Colheitadeira> colheitadeiras = new ArrayList<Colheitadeira>();
	
	
	public void removerColheitadeira(Colheitadeira colheitadeira) {
		this.colheitadeiras.remove(colheitadeira);		
	}
	
	public void adicionarColheitadeira(Colheitadeira colheitadeira) {
		if (!this.colheitadeiras.contains(colheitadeira)) { 
			this.colheitadeiras.add(colheitadeira);
			colheitadeira.setFazenda(this);
		}
	}
	
	public void adicionarCultivar(Cultivar cultivar) {
		cultivares.add(cultivar);
	}
	
	public List<Cultivar> getCultivares() {
		return cultivares;
	}
	
	public List<Colheitadeira> getColheitadeiras() {
		return colheitadeiras;
	}	
	
	public Fazenda(String nome, double areaEmHectares, double valorDeMercado) {
		if (nome == null || nome.trim().length()==0) {
			throw new RuntimeException("Nome não pode ser nulo nem vazio!");
		}
		if (areaEmHectares <= 0) {
			throw new RuntimeException("Área deve ser maior que zero!");
		}
		if (valorDeMercado <= 0) {
			throw new RuntimeException("Valor de mercado deve ser maior que zero!");
		}
		this.nome = nome;
		this.areaEmHectares = areaEmHectares;
		this.valorDeMercado = valorDeMercado;
	}	
	public void setValorDeMercado(double valorDeMercado) {
		this.valorDeMercado = valorDeMercado;
	}
	public double getValorDeMercado() {		
		return valorDeMercado;
	}
	public String getNome() {
		return nome;
	}
	public double getAreaEmHectares() {
		//double areaEmHectares = 0.00;
		return areaEmHectares;
	}


}
