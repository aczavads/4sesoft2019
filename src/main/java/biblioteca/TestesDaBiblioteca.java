package biblioteca;

import static org.junit.Assert.*;

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
	
	@Test(expected=RuntimeException.class)
	public void testarExceçãoNomeNuloAoConstruirBiblioteca() {
		Biblioteca nova = new Biblioteca(null);		
	}
	
	@Test
	public void testarSeNomeBibliotecaEhNulo() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");
		
		assertNotNull("Nome da biblioteca não pode ser nulo!", uniCesumarSede.getNome());
	}


}
