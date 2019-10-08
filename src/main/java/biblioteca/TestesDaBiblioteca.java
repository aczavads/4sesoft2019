package biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestesDaBiblioteca {

	@Test
	public void testarSeBibliotecaTemAcervo() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");

		Acervo acervo01 = new Acervo("Acervo Sede Maringá");
		uniCesumarSede.addAcervo(acervo01);
		
		Acervo acervo02 = new Acervo("Acervo Medicina");
		uniCesumarSede.addAcervo(acervo02);
		
		assertEquals("Deveria ter 2!!!", 2, uniCesumarSede.getAcervos().size());		
		assertTrue("Deveria ter o Acervo Sede Maringá!", uniCesumarSede.getAcervos().contains(acervo01));
		assertTrue("Deveria ter o Acervo Medicina", uniCesumarSede.getAcervos().contains(acervo02));
	}
/*
	@Test
	public void testarBibliotecaRepositoryFile() {
		Biblioteca uniCesumar = new Biblioteca("Biblioteca UniCesumar Campus Sede");		
		Biblioteca uem = new Biblioteca("Biblioteca Central UEM");		
		Biblioteca fcv = new Biblioteca("Biblioteca FCV UEM");
		
		BibliotecaRepository repo = new BibliotecaRepositoryFile();
		repo.truncate();
		repo.salvar(uniCesumar);
		repo.salvar(uem);
		
		assertEquals(2, repo.encontrarTodas().size());
		
		repo.salvar(fcv);
		assertEquals(3, repo.encontrarTodas().size());
	}
*/	
	@Test
	public void testarBibliotecaRepositoryAleatório() {
		Biblioteca uniCesumar = new Biblioteca("Biblioteca UniCesumar Campus Sede");		
		Biblioteca uem = new Biblioteca("Biblioteca Central UEM");		
		Biblioteca fcv = new Biblioteca("Biblioteca FCV UEM");
		
		BibliotecaRepository repo = BibliotecaRepository.createInstance();
		repo.truncate();
		repo.salvar(uniCesumar);
		repo.salvar(uem);
		
		assertEquals(2, repo.encontrarTodas().size());
		
		repo.salvar(fcv);
		assertEquals(3, repo.encontrarTodas().size());
	}
/*	
	@Test
	public void testarBibliotecaRepositoryDatabase() {
		Biblioteca uniCesumar = new Biblioteca("Biblioteca UniCesumar Campus Sede");		
		Biblioteca uem = new Biblioteca("Biblioteca Central UEM");		
		Biblioteca fcv = new Biblioteca("Biblioteca FCV UEM");
		
		BibliotecaRepository repo = new BibliotecaRepositoryDatabase();
		repo.truncate();
		repo.salvar(uniCesumar);
		repo.salvar(uem);
		
		assertEquals(2, repo.encontrarTodas().size());
		
		repo.salvar(fcv);
		assertEquals(3, repo.encontrarTodas().size());
	}
*/
	@Test(expected=RuntimeException.class)
	public void testarExceçãoNomeNuloAoConstruirBiblioteca() {
		Biblioteca nova = new Biblioteca(null);		
	}
	
	@Test
	public void testarSeNomeBibliotecaEstahCorreto() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");
		
		assertNotNull("Nome da biblioteca não pode ser nulo!", uniCesumarSede.getNome());
		assertEquals("UniCesumar Campus Sede", uniCesumarSede.getNome());
	}


}
