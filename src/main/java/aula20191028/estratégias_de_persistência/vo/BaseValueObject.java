package aula20191028.estratégias_de_persistência.vo;

public abstract class BaseValueObject {
	private String valor;
	
	public BaseValueObject(String valor) {
		this.valor = valor;
	}


	public String getValor() {
		return valor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseValueObject other = (BaseValueObject) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	
	
}
