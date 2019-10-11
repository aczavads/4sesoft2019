package aula20191010.polimorfismo;

public class Dragon implements Player, Flyable {

	@Override
	public int attackLevel() {
		return 100;
	}

	@Override
	public int defenseLevel() {
		return 3;
	}

	@Override
	public void fly() {
		System.out.println("Flap flap... flying!");
	}

}
