package biblioteca;


public class Livro implements Publicação {

	private String título;
	private String autor;
	private int anoDePublicação;

	public Livro(String título, String autor, int anoDePublicação) {
		this.título = título;
		this.autor = autor;
		this.anoDePublicação = anoDePublicação;
	}
	
	public int getAnoDePublicação() {
		return anoDePublicação;
	}
	public String getAutor() {
		return autor;
	}
	public String getTítulo() {
		return título;
	}

}
