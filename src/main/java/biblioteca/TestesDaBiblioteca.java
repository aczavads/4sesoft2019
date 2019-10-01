package biblioteca;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesDaBiblioteca {

	@Test
	public void testarSeBibliotecaTemAcervo() {
		Biblioteca uniCesumarSede = new Biblioteca("UniCesumar Campus Sede");
		uniCesumarSede.addAcervo(new Acervo("Acervo Sede Maringá"));
		uniCesumarSede.addAcervo(new Acervo("Acervo Medicina"));		
		assertEquals(2, uniCesumar.getAcervos().size());
	}

}
