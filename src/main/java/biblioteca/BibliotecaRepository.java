package biblioteca;

import java.util.List;

public interface BibliotecaRepository {

	void truncate();
	void salvar(Biblioteca biblioteca);
	List<Biblioteca> encontrarTodas();
	void excluirPeloNome(String nome);
	
	
	static BibliotecaRepository createInstance() {
		final boolean momentoEhPar = System.currentTimeMillis()%2 == 0;
		if (momentoEhPar) {
			return new BibliotecaRepositoryDatabase();
		} else {
			return new BibliotecaRepositoryFile();
		}
	}
	

}
