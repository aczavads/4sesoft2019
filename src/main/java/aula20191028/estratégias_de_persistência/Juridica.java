package aula20191028.estratégias_de_persistência;

import aula20191028.estratégias_de_persistência.vo.CNPJ;
import aula20191028.estratégias_de_persistência.vo.InscricaoEstadual;

public class Juridica extends Pessoa {
	private String razaoSocial;
	private CNPJ cnpj;
	private InscricaoEstadual inscricaoEstadual;
	
	public Juridica(String id, String nome, String telefoneMovel, String telefoneFixo, CNPJ cnpj, InscricaoEstadual inscricaoEstadual, String razaoSocial) {
		super(id, nome, telefoneMovel, telefoneFixo);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.razaoSocial = razaoSocial;
	}	
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public CNPJ getCnpj() {
		return cnpj;
	}
	public void setCnpj(CNPJ cnpj) {
		this.cnpj = cnpj;
	}
	public InscricaoEstadual getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(InscricaoEstadual inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	
	

}
