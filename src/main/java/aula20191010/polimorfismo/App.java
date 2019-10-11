package aula20191010.polimorfismo;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	public static void main(String[] args) {
		
		List<Player> players = createPlayers();
		int totalAttackLevel = 0;
		int totalDefenseLevel = 0;
		for (Player player : players) {
			totalAttackLevel += player.attackLevel();
			totalDefenseLevel += player.defenseLevel();
			if (player instanceof Dragon) {
				//cast: referenciar um objeto usando uma referência menos abstrata a partir de uma referência mais abstrata 
				Flyable f = (Flyable) player;
				Dragon d = (Dragon) player;
				f.fly();
			}
		}
		System.out.println("Attack: " + totalAttackLevel + " Defense: " + totalDefenseLevel);
	}

	private static List<Player> createPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Dragon());
		players.add(new Snake());
		players.add(new Zombie());
		players.add(new Wizard());
		players.add(new Wizard());
		players.add(new Wizard());
		players.add(new Worm());
		return players;
	}

}
