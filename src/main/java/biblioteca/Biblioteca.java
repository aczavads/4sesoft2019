package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Biblioteca implements Serializable {
	private String id;
	private String nome;
	private List<Acervo> acervos = new ArrayList<Acervo>();
	
	
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
