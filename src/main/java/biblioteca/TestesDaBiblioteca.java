package biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.ValidationEvent;

import org.junit.Test;

public class TestesDaBiblioteca {
	
	@Test
	public void testarSeBibliotecaSãoIguais() {
		Biblioteca b1 = new Biblioteca("9c0ec343-483f-40cf-987e-120fd1356441", "Biblioteca Municipal de Maringá");
		Biblioteca b2 = new Biblioteca("9c0ec343-483f-40cf-987e-120fd1356441", "Biblioteca Municipal de Maringá");
		Biblioteca b3 = b2;
		Biblioteca b4 = null;
		Biblioteca b5 = new Biblioteca("Biblioteca Municipal de Maringá");
		
		assertNotSame(b1, b2);
		assertSame(b2, b3);
		assertEquals(b1, b2);
		assertNotEquals(b4, b1);
		assertNotEquals(b4, b2);
		assertNotEquals(b4, b3);
		assertEquals(b4, null);
		
		assertNotEquals(b5, b1);
		assertNotEquals(b5, b2);
	}

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

	@Test
	public void testarExclusãoDeBilioteca() {
		Biblioteca uniCesumar = new Biblioteca("Biblioteca UniCesumar Campus Sede");
		Biblioteca uem = new Biblioteca("Biblioteca Central UEM");
		Biblioteca fcv = new Biblioteca("Biblioteca FCV");

		String idDaUEM = uem.getId();
		String idDaFCV = fcv.getId();

		BibliotecaRepository repo = BibliotecaRepository.createInstance();
		repo.truncate();
		repo.salvar(uniCesumar);
		repo.salvar(uem);
		repo.salvar(fcv);
		assertEquals(3, repo.encontrarTodas().size());

		repo.excluirPeloId(idDaFCV);
		repo.excluirPeloId(idDaUEM);
		assertEquals(1, repo.encontrarTodas().size());

	}

	@Test
	public void testarBibliotecaRepositoryAleatório() {
		Biblioteca uniCesumar = new Biblioteca("Biblioteca UniCesumar Campus Sede");
		Biblioteca uem = new Biblioteca("Biblioteca Central UEM");
		Biblioteca fcv = new Biblioteca("Biblioteca FCV");

		BibliotecaRepository repo = BibliotecaRepository.createInstance();
		repo.truncate();
		repo.salvar(uniCesumar);
		repo.salvar(uem);

		assertEquals(2, repo.encontrarTodas().size());
		assertEquals(uniCesumar.getId(), repo.encontrarPeloId(uniCesumar.getId()).getId());
		assertEquals(uem.getId(), repo.encontrarPeloId(uem.getId()).getId());

		repo.salvar(fcv);
		assertEquals(3, repo.encontrarTodas().size());
		assertEquals(fcv.getId(), repo.encontrarPeloId(fcv.getId()).getId());
	}

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

	@Test(expected = RuntimeException.class)
	public void testarExceçãoNomeNuloAoConstruirBiblioteca() {
		Biblioteca nova = new Biblioteca(null);
	}

	@Test
	public void testarSeNomeBibliotecaEstahCorreto() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");

		assertNotNull("Nome da biblioteca não pode ser nulo!", uniCesumarSede.getNome());
		assertEquals("UniCesumar Campus Sede", uniCesumarSede.getNome());
	}
	
	@Test 
	public void testarIncorporaçãoDeItemAoAcervo() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");
		
		Acervo acervo01 = new Acervo("Acervo Sede Maringá");
		uniCesumarSede.addAcervo(acervo01);
		
		Local c2p1 = new Local("C2P1");
		acervo01.incorporar(new Livro("Object Oriented Analysis and Design","Grady Booch", 1999), c2p1);
		acervo01.incorporar(new Livro("UML Applied", "Grady Booch", 2010), c2p1);
		acervo01.incorporar(new Livro("UML Applied", "Outro Autor de Albuquerque", 2016), c2p1);
		
		assertEquals(1, acervo01.pesquisarPorTitulo("Object Oriented Analysis and Design").size());
		assertEquals(2, acervo01.pesquisarPorTitulo("UML Applied").size());
		
		assertEquals(1, acervo01.pesquisarPorAutor("Outro Autor de Albuquerque").size());
		assertEquals(2, acervo01.pesquisarPorAutor("Grady Booch").size());
		
	}
	

}





