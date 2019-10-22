package aula20191022.equals;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesComPedido {

	@Test	
	public void testarNãoIguais() {
		Pedido p1 = new Pedido();
		Pedido p2 = new Pedido();
		
		assertNotEquals(p1, p2);		
	}
	@Test	
	public void testarIguais() {
		Pedido p1 = new Pedido("77a21839-bc78-4cad-ae56-abe387400517");
		p1.setValor(100.33);
		
		Pedido p2 = new Pedido("77a21839-bc78-4cad-ae56-abe387400517");
		p2.setValor(200.88);
		
		assertEquals(p1, p2);		
	}

}
