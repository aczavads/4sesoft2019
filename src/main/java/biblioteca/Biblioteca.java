package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private String nome;
	private List<Acervo> acervos = new ArrayList<Acervo>();

	public Biblioteca(String nome) {
		if (nome == null) {
			throw new RuntimeException("O nome não deve ser nulo!");
		}
		this.nome = nome;
	}

	public void addAcervo(Acervo acervo) {
		acervos.add(acervo);
	}

	public List<Acervo> getAcervos() {
		return acervos;
	}

	public String getNome() {
		return nome;
	}


}
