package aula20191028.estratégias_de_persistência;

import static org.junit.Assert.*;

import org.junit.Test;

import aula20191028.estratégias_de_persistência.repository.PessoaRepository;
import aula20191028.estratégias_de_persistência.vo.CNPJ;
import aula20191028.estratégias_de_persistência.vo.CPF;
import aula20191028.estratégias_de_persistência.vo.InscricaoEstadual;
import aula20191028.estratégias_de_persistência.vo.RG;

public class TestesComPessoas {

	@Test
	public void testarPersistênciaSingleTable() {
		PessoaRepository repo = new PessoaRepositorySingleTable(ConnectionFactory.createConnection());

		repo.truncate();
		
		Fisica ana = new Fisica("00ca59fd-605c-4150-bad5-80174af7aa8b", "Ana Lúcia", "(44) 93333-5541",
				"(44) 3365-4544", new CPF("546.541.554-4"), new RG("1.123.541-4"));
		
		Juridica big = new Juridica("6c14e7b1-aa86-43db-bffc-ae224a07118b", "Big Maringá", "(44) 93333-9999",
				"(44) 6661-1354", new CNPJ("01.546.544/0001-54"), new InscricaoEstadual("ISENTO"), "Walmart Maringá Big Regional");
		
		repo.salvar(ana);
		repo.salvar(big);
		
		assertEquals(ana, repo.encontrarPeloId(ana.getId()));
		assertEquals(big, repo.encontrarPeloId(big.getId()));
	}
	
	@Test
	public void testarPersistênciaTablePerClass() {
		PessoaRepository repo = new PessoaRepositoryTablePerClass(ConnectionFactory.createConnection());

		repo.truncate();
		
		Fisica ana = new Fisica("00ca59fd-605c-4150-bad5-80174af7aa8b", "Ana Lúcia", "(44) 93333-5541",
				"(44) 3365-4544", new CPF("546.541.554-4"), new RG("1.123.541-4"));
		
		Juridica big = new Juridica("6c14e7b1-aa86-43db-bffc-ae224a07118b", "Big Maringá", "(44) 93333-9999",
				"(44) 6661-1354", new CNPJ("01.546.544/0001-54"), new InscricaoEstadual("ISENTO"), "Walmart Maringá Big Regional");
		
		repo.salvar(ana);
		repo.salvar(big);
		
		assertEquals(ana, repo.encontrarPeloId(ana.getId()));
		assertEquals(big, repo.encontrarPeloId(big.getId()));
	}
	
	@Test
	public void testarPersistênciaJoined() {
		PessoaRepository repo = new PessoaRepositoryJoined(ConnectionFactory.createConnection());

		repo.truncate();
		
		Fisica ana = new Fisica("00ca59fd-605c-4150-bad5-80174af7aa8b", "Ana Lúcia", "(44) 93333-5541",
				"(44) 3365-4544", new CPF("546.541.554-4"), new RG("1.123.541-4"));
		
		Juridica big = new Juridica("6c14e7b1-aa86-43db-bffc-ae224a07118b", "Big Maringá", "(44) 93333-9999",
				"(44) 6661-1354", new CNPJ("01.546.544/0001-54"), new InscricaoEstadual("ISENTO"), "Walmart Maringá Big Regional");
		
		repo.salvar(ana);
		repo.salvar(big);
		
		assertEquals(ana, repo.encontrarPeloId(ana.getId()));
		assertEquals(big, repo.encontrarPeloId(big.getId()));
	}

}
