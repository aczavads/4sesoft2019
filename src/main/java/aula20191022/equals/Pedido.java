package aula20191022.equals;

import java.util.Date;
import java.util.UUID;

public class Pedido {
	private String id;
	private Date emissao;
	private double valor;
	
	public Pedido() {
		id = UUID.randomUUID().toString();
	}	
	public Pedido(String id) {
		this.id = id;
	}
	public Pedido(String id, Date emissao, double valor) {
		this.id = id;
		this.emissao = emissao;
		this.valor = valor;
	}
	public String getId() {
		return id;
	}
	public Date getEmissao() {
		return emissao;
	}
	public double getValor() {
		return valor;
	}
	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof Pedido)) {
			return false;
		}
		Pedido otherAsPedido = (Pedido) other;
		
		String otherId = otherAsPedido.getId();
		if (this.id == null && otherAsPedido.id == null) {
			return true;
		}
		if ((this.id == null && otherAsPedido !=null) || otherAsPedido.id == null && this.id != null) {
			return false;
		}			
		if (otherId.equals(this.id)) {
			return true;
		}
		return false;
	}

}
