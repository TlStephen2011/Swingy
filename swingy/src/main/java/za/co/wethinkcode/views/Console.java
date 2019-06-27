package za.co.wethinkcode.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import za.co.wethinkcode.models.Armor;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Helm;
import za.co.wethinkcode.models.Hero;
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
	
	public String[] showHeroMenu() throws Exception {
		//TODO present hero options from file
		ArrayList<String> heroes = new ArrayList<String>();
		String[] str = this.getAvailableHeroes();
		
		if (str.length == 0) {
			return null;
		} else {
			System.out.println("These are your available heroes:");
			
			//TODO print hero details correctly
			for (int i = 0; i < str.length; i++) {
				String[] split = str[i].split(",");
				System.out.println(i + 1 + ": " + split[0].trim());
			}
			System.out.println("Select an existing index to start the game with the selected hero or select 0 to create a new hero.");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int index = Integer.parseInt(in.readLine());
			if (index == 0) {
				return null;
			} else {
				return str[index - 1].split(",");
			}
		}
	}
	
	public inputType getInput() {
		BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            
            String input = br.readLine();
            input = input.toUpperCase();
            
            if (input.equals("N")) {
            	return inputType.NORTH;
            } else if (input.equals("S")) {
            	return inputType.SOUTH;
            } else if (input.equals("W")) {
            	return inputType.WEST;
            } else if (input.equals("E")) {
            	return inputType.EAST;
            } else if (input.equals("FIGHT")) {
            	return inputType.FIGHT;
            } else if (input.equals("RUN")) {
            	return inputType.RUN;
            } else if (input.equals("TAKE")) {
            	return inputType.TAKE_ITEM;
            } else {
            	return inputType.UNKNOWN;
            }
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		return inputType.UNKNOWN;
	}

	public void display(GameBoard g, String x) {
		System.out.println(x);
	}

	public Hero newHero() {
		
		while (true) {
			try {
				String[] hero = this.showHeroMenu();
				if (hero == null) {
					hero = this.createNewHero();
					return new Hero(hero[0].trim(), hero[1].trim());
				} else {
					return new Hero(
							hero[0].trim(),
							hero[1].trim(),
							Integer.parseInt(hero[2].trim()),
							Integer.parseInt(hero[3].trim()),
							Integer.parseInt(hero[4].trim()),
							Integer.parseInt(hero[5].trim()),
							Integer.parseInt(hero[6].trim()),
							new Coordinates(0, 0),
							new Weapon(hero[7].trim(), 15),
							new Armor(hero[8].trim(), 30),
							new Helm(hero[9].trim(), 100)
					);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
