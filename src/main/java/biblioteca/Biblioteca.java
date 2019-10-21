package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Biblioteca implements Serializable {
	private String id;
	private String nome;
	private List<Acervo> acervos = new ArrayList<Acervo>();
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	public boolean equals(Object outro) {
		if (outro == null) {
			return false;
		}
		if (this == outro) {
			return true;
		}
		if (!(outro instanceof Biblioteca)) {
			return false;
		}
		
		//Coerção de referências ou type casting (cast)
		Biblioteca outraBiblioteca = (Biblioteca) outro;
		
		if (this.id.equals(outraBiblioteca.id) && this.nome.equals(outraBiblioteca.nome)) {
			return true;
		}		
		
		return false;
	}
	
	public Biblioteca(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Biblioteca(String nome) {
		id = UUID.randomUUID().toString();
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
	
	public String getId() {
		return id;
	}

}
