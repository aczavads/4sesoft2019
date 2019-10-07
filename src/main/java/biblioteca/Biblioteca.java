package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca implements Serializable {
	private String nome;
	private List<Acervo> acervos = new ArrayList<Acervo>();
	
	
	public Biblioteca(String nome) {
		if (nome == null) {
			throw new RuntimeException("Nome não pode ser nulo!");
		}
		this.nome = nome;
	}
	
	public void addAcervo(Acervo acervo) {
		this.acervos.add(acervo);
	}
	
	public List<Acervo> getAcervos() {
		return acervos;
	}
	
	public String getNome() {
		return nome;
	}

}
