package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaRepositoryFile implements BibliotecaRepository {
	private static final String NOME_DO_ARQUIVO = "d:\\arthur\\biblioteca.dat";

	public BibliotecaRepositoryFile() {
		//System.out.println("Inicializando um repository <<FILE>>!");
	}

	public void truncate() {
		try {
			File arquivo = new File(NOME_DO_ARQUIVO);
			arquivo.delete();
		} catch (Exception e) {
		}
	}

	public void salvar(Biblioteca biblioteca) {
		List<Biblioteca> dados;
		try {
			dados = carregar();
		} catch (Exception e) {
			dados = new ArrayList<>();
		}
		dados.add(biblioteca);
		persistir(dados);
	}

	public List<Biblioteca> encontrarTodas() {
		try {
			return carregar();
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	private void persistir(List<Biblioteca> dados) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NOME_DO_ARQUIVO))) {
			out.writeObject(dados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Biblioteca> carregar() throws Exception {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(NOME_DO_ARQUIVO))) {
			return (List<Biblioteca>) in.readObject();
		} catch (Exception e) {
			return new ArrayList<>();
			// e.printStackTrace();
		}
	}

	@Override
	public void excluirPeloId(String id) {
		List<Biblioteca> dados;
		try {
			dados = carregar();

			
			Biblioteca aux = null;
			for (Biblioteca biblioteca : dados) {
				if (biblioteca.getId().equals(id)) {
					aux = biblioteca;
				}
			}
			dados.remove(aux);
						
			
			//dados = dados.stream().filter( b -> !b.getNome().equals(nome) ).collect(Collectors.toList());
			
		} catch (Exception e) {
			dados = new ArrayList<>();
		}
		persistir(dados);
	}

	@Override
	public Biblioteca encontrarPeloId(String id) {
		try {
			List<Biblioteca> dados = carregar();			
			for (Biblioteca biblioteca : dados) {
				if (biblioteca.getId().equals(id)) {
					return biblioteca;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
