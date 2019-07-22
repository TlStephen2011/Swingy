package za.co.wethinkcode.utilities;

import java.util.ArrayList;
import java.util.Random;

import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.Villain;

public class VillainBuilder {

	public static ArrayList<Villain> buildVillains(int level, int num) {
		ArrayList<Villain> villains = new ArrayList<Villain>();
		String[] names = new String[]{"Leslie", "Thomas", "Peter","John", "Jake", "Phillip", "Rick", "Arnold", "Harry"};
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<Artifact> artifacts = ArtifactBuilder.buildArtifacts(level, num / 2);
		
		for (int i = 0; i < num / 2; i++) {
			villains.add(new Villain(names[rand.nextInt(9)],
									(level - (rand.nextInt(level + 1) - (level + 1 ) / 2)),
									(rand.nextInt(level + 1) + 1) * level,
									(rand.nextInt(level + 1) + 1) * level,
									(rand.nextInt(level + 1) + 1) * level,
									null,
									artifacts.get(i)));
			
			Coordinates coords;
			Villain currentVillain = villains.get(i);
			coords = new Coordinates(rand.nextInt(num), rand.nextInt(num));
			
			for (int j = 0; j < villains.size(); j++) {
				Coordinates tempCoord = villains.get(j).getPosition();
				if (tempCoord == null) {
					tempCoord = new Coordinates(-1, -1);
				}
				if ((tempCoord.getRow() == coords.getRow() && tempCoord.getCol() == coords.getCol()) ||
					(coords.getRow() == num / 2 && coords.getCol() == num / 2)) {
					coords = new Coordinates(rand.nextInt(num), rand.nextInt(num));
					j = 0;
				}
			}
			currentVillain.setPosition(coords);
		}
		
		return villains;
	}
}
