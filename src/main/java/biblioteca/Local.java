package biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Local {
	private String identificação;
	private List<ItemDoAcervo> itens = new ArrayList<>();		

	public Local(String identificação) {
		this.identificação = identificação;
	}
	
	public String getIdentificação() {
		return identificação;
	}

	public void incorporar(Publicação publicação) {
		this.itens.add(new ItemDoAcervo(publicação));
	}

	public List<ItemDoAcervo> getItens() {
		//return itens;
		//return new ArrayList<>(itens);
		return Collections.unmodifiableList(itens);
	}
}
