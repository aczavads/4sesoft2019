package aula20190909.associações;

public class App {
	
	public static void main(String[] args) {
		Fazenda f1 = new Fazenda("Rancho Fundo", 80, 125000.00);
		Fazenda f2 = new Fazenda("Rancho Raso", 80, 125000.00);
		
		Colheitadeira c1 = new Colheitadeira("aaa-5554", 200d, 5000d);
		
		f1.adicionarColheitadeira(c1);

		try {
			imprimirColheitadeirasDaFazendaNoConsole(f1);
			System.out.println("A colheitadeira aaa-5554 está na fazenda: " + c1.getFazenda().getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		c1.setFazenda(f2);
		imprimirColheitadeirasDaFazendaNoConsole(f1);
		imprimirColheitadeirasDaFazendaNoConsole(f2);
		System.out.println("A colheitadeira aaa-5554 está na fazenda: " + c1.getFazenda().getNome());
		
		
	}

	private static void imprimirColheitadeirasDaFazendaNoConsole(Fazenda f1) {
		System.out.println("Colheitadeiras da fazenda "+ f1.getNome()+">>>>>>>>>>>>>>>>>>>>>>>>>>");
		for (Colheitadeira c : f1.getColheitadeiras()) {
			System.out.println(c.getNumeroDeSerie());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

}
