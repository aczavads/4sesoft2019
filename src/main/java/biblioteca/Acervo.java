package biblioteca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Acervo {
	private String descrição;
	
	//cuidado aqui!	
	private Set<Local> locais = new HashSet();
	
	public Acervo(String descrição) {
		this.descrição = descrição;
	}

	public void incorporar(Publicação publicação, Local local) {
		local.incorporar(publicação);
		//adicionar o local...
		locais.add(local);
	}

	public List<Publicação> pesquisarPorTitulo(String títuloParaPesquisar) {
		//pesquisar dentre os itens do local...
		List<Publicação> publicações = new ArrayList<>();
		for (Local local : locais) {
			for(ItemDoAcervo item : local.getItens()) {
				if (item.getPublicação().getTítulo().equalsIgnoreCase(títuloParaPesquisar)) {
					//System.out.println("achou!!! " + item.getPublicação().getTítulo());
					publicações.add(item.getPublicação());
				}
			}
		}
		return publicações;
	}
	
	public List<Publicação> pesquisarPorAutor(String títuloParaPesquisar) {
		//pesquisar dentre os itens do local...
		List<Publicação> publicações = new ArrayList<>();
		for (Local local : locais) {
			for(ItemDoAcervo item : local.getItens()) {
				if (item.getPublicação().getAutor().equalsIgnoreCase(títuloParaPesquisar)) {
					//System.out.println("achou!!! " + item.getPublicação().getTítulo());
					publicações.add(item.getPublicação());
				}
			}
		}
		return publicações;
	}

}
