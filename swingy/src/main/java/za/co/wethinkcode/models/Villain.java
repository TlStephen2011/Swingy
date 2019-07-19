package za.co.wethinkcode.models;

import java.util.Random;

import za.co.wethinkcode.utilities.Coordinates;

public class Villain extends Character {
	private boolean hasArtifact;
	private Artifact artifact;
	
	public Villain(String name,
					int level,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position,
					Artifact a) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);
		if (a != null) {
			this.artifact = a;
			this.hasArtifact = true;
		} else {
			this.hasArtifact = false;
		}
	}
	
	public Villain(String name,
					int level,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);
		this.hasArtifact = false;
	}
	
	public Artifact dropArtifact() {
		if (this.hasArtifact) {
			Random rand = new Random(System.currentTimeMillis());
			
			if ((rand.nextInt() % 100 + 1) % 2 == 0) {
				return this.artifact;
			}
		}
		return null;
	}
	
	public int getLevel() {
		return this.level;
	}
}
