package aula20191028.estratégias_de_persistência.repository;

import java.util.List;

import aula20191028.estratégias_de_persistência.Pessoa;

public interface PessoaRepository {
	
	void salvar(Pessoa p);
	List<Pessoa> recuperarTodas();
	void truncate();
	void excluirPeloId(String id);
	Pessoa encontrarPeloId(String id);
	

}
