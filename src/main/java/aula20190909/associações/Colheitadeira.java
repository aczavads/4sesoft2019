package aula20190909.associações;

public class Colheitadeira {
	private final String numeroDeSerie;
	private final double capacidadeDoTanqueEmLitros;
	private final double capacidadeDeCargaEmKilos;
	private String apelidoCarinhoso;
	private Fazenda fazenda;
	
	public Colheitadeira(String numeroDeSerie, double capacidadeDoTanqueEmLitros, double capacidadeDeCargaEmKilos, String apelidoCarinhoso) {
		this(numeroDeSerie, capacidadeDoTanqueEmLitros, capacidadeDeCargaEmKilos);
		this.apelidoCarinhoso = apelidoCarinhoso;
	}
	
	public Colheitadeira(String numeroDeSerie, double capacidadeDoTanqueEmLitros, double capacidadeDeCargaEmKilos) {
		this.numeroDeSerie = numeroDeSerie;
		this.capacidadeDeCargaEmKilos = capacidadeDeCargaEmKilos;
		this.capacidadeDoTanqueEmLitros = capacidadeDoTanqueEmLitros;
	}
	
	public String getApelidoCarinhoso() {
		return apelidoCarinhoso;
	}
	public double getCapacidadeDeCargaEmKilos() {
		return capacidadeDeCargaEmKilos;
	}
	public double getCapacidadeDoTanqueEmLitros() {
		return capacidadeDoTanqueEmLitros;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setApelidoCarinhoso(String novoApelidoCarinhoso) {
		apelidoCarinhoso = novoApelidoCarinhoso;
	}

	public Fazenda getFazenda() {
		return this.fazenda;
	}
	
	public void setFazenda(Fazenda fazenda) {
		if (this.fazenda != null && this.fazenda != fazenda) {
			this.fazenda.removerColheitadeira(this);
		}
		this.fazenda = fazenda;
		this.fazenda.adicionarColheitadeira(this);
	}

}
