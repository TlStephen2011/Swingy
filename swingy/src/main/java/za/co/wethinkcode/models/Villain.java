package za.co.wethinkcode.models;

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
	
}
