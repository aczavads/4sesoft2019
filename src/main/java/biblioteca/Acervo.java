package biblioteca;

import java.util.List;

public class Acervo {
	private String descrição;
	
	//cuidado aqui!	
	private List<Local> locais;
	
	public Acervo(String descrição) {
		this.descrição = descrição;
	}

	public void incorporar(Publicação publicação, Local local) {
		local.incorporar(publicação);
		//adicionar o local...
	}

	public List<Publicação> pesquisarPorTitulo(String títuloParaPesquisar) {
		//pesquisar dentre os itens do local...
		return null;
	}
	
	public List<Publicação> pesquisarPorAutor(String títuloParaPesquisar) {
		//pesquisar dentre os itens do local...
		return null;
	}

}
