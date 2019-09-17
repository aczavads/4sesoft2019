package aula20190917.mapas;

public class Venda {
	private String siglaDoEstado;
	private Double valor;

	public Venda(String siglaDoEstado, Double valor) {
		super();
		this.siglaDoEstado = siglaDoEstado;
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public String getSiglaDoEstado() {
		return siglaDoEstado;
	}

	
	
}


