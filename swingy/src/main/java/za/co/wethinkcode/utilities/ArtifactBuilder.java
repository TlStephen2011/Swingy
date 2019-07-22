package za.co.wethinkcode.utilities;

import java.util.ArrayList;
import java.util.Random;

import za.co.wethinkcode.models.Armor;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.Helm;
import za.co.wethinkcode.models.Weapon;

public class ArtifactBuilder {
	
	private static String[] weapons = {
			"Buster Sword",
			"211-V Plasma",
			"Master Sword",
			"Fire Flower",
			"Golden Gun",
			"Gravity Gun",
			"BFG 9000"
	};
	
	private static String[] helms = {
			"Boar Tusk",
			"Leoric Crown",
			"Veil of Steel",
			"Harlequin Crest",
			"The Undead Crown"
	};
	
	private static String[] armor = {
			"Banded mail",
			"Bases",
			"Boiled Leather Armor",
			"Jack of Plate",
			"Plated mail"
	};
	
	public static ArrayList<Artifact> buildArtifacts(int level, int number) {
		ArrayList<Artifact> a = new ArrayList<Artifact>();
		Random rand = new Random(System.currentTimeMillis());
		String[] types = {"weapon", "helm", "armor"};
		
		for (int i = 0; i < number; i++) {
			int type = Math.abs(rand.nextInt() % 3);
			int namePos = 0;
			
			if (types[type].equals("weapon")) {
				namePos = Math.abs(rand.nextInt() % weapons.length);				
				a.add(new Weapon(weapons[namePos], (int)Math.pow(level, 2) + level * 5));				
			} else if (types[type].equals("helm")) {
				namePos = Math.abs(rand.nextInt() % helms.length);
				a.add(new Helm(helms[namePos], (int)Math.pow(level, 2) + level * 10));
			} else if (types[type].equals("armor")) {
				namePos = Math.abs(rand.nextInt() % armor.length);
				a.add(new Armor(armor[namePos], (int)Math.pow(level, 2) + level * 7));
			}		
		}
		
		return a;
	}
}
