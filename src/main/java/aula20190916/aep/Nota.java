package aula20190916.aep;

public class Nota {

	private double valor;

	public Nota(double valor) {
		if (valor < 0 || valor > 10) {
			throw new NotaForaDoIntervaloException("A nota deve estar entre 0.0 e 10.0");
		}
		this.valor = valor;
	}
	
	public double getValor() {
		return valor;
	}
	
	

}
