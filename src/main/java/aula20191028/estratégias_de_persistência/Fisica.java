package aula20191028.estratégias_de_persistência;

import aula20191028.estratégias_de_persistência.vo.CPF;
import aula20191028.estratégias_de_persistência.vo.RG;

public class Fisica extends Pessoa {
	private RG rg;
	private CPF cpf;

	public Fisica(String id, String nome, String telefoneMovel, String telefoneFixo, CPF cpf, RG rg) {
		super(id, nome, telefoneMovel, telefoneFixo);
		this.rg = rg;
		this.cpf = cpf;
	}

	public RG getRg() {
		return rg;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public void setRg(RG rg) {
		this.rg = rg;
	}

}
