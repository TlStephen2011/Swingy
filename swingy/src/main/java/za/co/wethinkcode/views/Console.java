package za.co.wethinkcode.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Armor;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Helm;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import za.co.wethinkcode.models.Weapon;
import za.co.wethinkcode.utilities.Coordinates;

public class Console implements Viewable {

	public Console() {

	}

	public String[] createNewHero() {
		String[] str = new String[2];
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter hero name: ");
			str[0] = br.readLine();
			System.out.println("Enter hero class: ");
			str[1] = br.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return str;
	}

	public String[] getAvailableHeroes() {
		ArrayList<String> lines = new ArrayList<String>();
		String line;

		try {
			BufferedReader objReader = new BufferedReader(new FileReader("heroes.txt"));

			while ((line = objReader.readLine()) != null) {
				lines.add(line);
			}

			objReader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String[] str = lines.toArray(new String[0]);
		return str;
	}

	public Hero showHeroMenu() throws Exception {
		// TODO present hero options from file
		ArrayList<String> heroes = new ArrayList<String>();
		String[] str = this.getAvailableHeroes();
		ArrayList<Hero> savedHeroes = HeroStorage.getAllHeroes();
		
		if (savedHeroes.size() == 0) {
			return null;
		} else {
			System.out.println("These are your available heroes:");

			Hero chosenOne = null;
			
			for (int i = 0; i < savedHeroes.size(); i++) {
				System.out.println(i + 1 + " " + savedHeroes.get(i).getHeroName());		
			}
		
			System.out.println(
					"Select an existing index to start the game with the selected hero or select 0 to create a new hero.");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int index = Integer.parseInt(in.readLine());			
			if (index == 0) {
				return null;
			} else {
				return savedHeroes.get(index - 1);
			}
		}
	}

	private inputType getInput() {
		BufferedReader br = null;
		Viewable.inputType in = inputType.UNKNOWN;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		while (in == inputType.UNKNOWN) {
			try {
				String input = br.readLine();
				input = input.toUpperCase();

				if (input.equals("N")) {
					in = inputType.NORTH;
				} else if (input.equals("S")) {
					in = inputType.SOUTH;
				} else if (input.equals("W")) {
					in = inputType.WEST;
				} else if (input.equals("E")) {
					in = inputType.EAST;
				} else if (input.equals("FIGHT")) {
					in = inputType.FIGHT;
				} else if (input.equals("RUN")) {
					in = inputType.RUN;
				} else if (input.equals("TAKE")) {
					in = inputType.TAKE_ITEM;
				} else if (input.equals("LEAVE")) {
					in = inputType.LEAVE_ITEM;
				} else if (input.equals("QUIT")) {
					in = inputType.QUIT;
				} else {
					System.out.println("Invalid input");
					in = inputType.UNKNOWN;
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		return in;
	}

	public void display(GameBoard g, String x) {
		System.out.println(x);
	}

	public Hero newHero() {

		while (true) {
			try {
				String[] hero = null;
				Hero loadedHero = this.showHeroMenu();
				if (loadedHero == null) {
					hero = this.createNewHero();
					return new Hero(hero[0].trim(), hero[1].trim());
				} else {
					return loadedHero;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public inputType showEnemyEncounter(Villain villain) {
		System.out.println("You have encountered a villain: ");
		System.out.println(villain.toString());
		System.out.println("Do you want to fight or run? [FIGHT]/[RUN]");
		Viewable.inputType choice = this.getInput();
		while (choice != inputType.FIGHT && choice != inputType.RUN) {
			System.out.println("You have entered an invalid option. You can either [FIGHT] or [RUN].");
			choice = this.getInput();
		}
		return choice;
	}

	public inputType getMovementDirection() {
		Viewable.inputType in = this.getInput();

		while (in != Viewable.inputType.NORTH &&
			   in != Viewable.inputType.SOUTH &&
			   in != Viewable.inputType.WEST &&
			   in != Viewable.inputType.EAST &&
			   in != inputType.QUIT) {
			System.out.println("You have entered an invalid direction");
			System.out.println("Accepted commands [N, S, E, W]");
			in = this.getInput();
		}
		return in;
	}

	public void showRun(boolean success) {
		if (success == true) {
			System.out.println("You have managed to escape this time. Next time you might not be so lucky!");
		} else {
			System.out.println("You were too slow, you are doomed to fight to survive.");
		}
	}

	public void showDeath(Villain v, Hero h) {
		System.out.println(v.toString());
		System.out.println("Has defeated you.");
		System.out.println("You died a hero, your stats were:");
		System.out.println(h.toString());
	}

	public void showWonFight(Villain v, Hero h) {
		System.out.println("You managed to defeat the beast.");
	}

	public inputType showArtifactDropped(Artifact a) {
		System.out.println("You are presented with an artifact.");
		System.out.println(a.toString());
		System.out.println("You can either [TAKE] or [LEAVE] the item.");
		inputType t = this.getInput();
		if (t != inputType.TAKE_ITEM && t != inputType.LEAVE_ITEM) {
			System.out.println("Invalid input. You can either [TAKE] or [LEAVE] the item.");
			t = this.getInput();
		}
		return t;
	}

	public void showWonCurrentMap() {
		System.out.println("You have entered the void, you are suddenly placed on a new map.");
	}

	public void showLevelUp(Hero h) {
		System.out.println("You have leveled up!. Current level is " + h.getLevel());
	}
	
	public void showHeroStats(Hero h) {
		System.out.println("Hero Stats");
		System.out.println(h.toString());
	}
	
	public void showGameQuit() {
		System.out.println("Your game has been saved. Goodbye.");
	}
}
