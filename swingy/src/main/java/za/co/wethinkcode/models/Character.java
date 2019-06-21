package za.co.wethinkcode.models;

import utilities.Coordinates;

public abstract class Character {
	protected String name;
	protected int level;
	protected int attackDamage;
	protected int defensePoints;
	protected int hitPoints;
	protected Coordinates position;
	
	public Character(	String name,
						int level,
						int attackDamage,
						int defensePoints,
						int hitPoints,
						Coordinates position) {
		this.name = name;
		this.level = level;
		this.attackDamage = attackDamage;
		this.defensePoints = defensePoints;
		this.hitPoints = hitPoints;
		this.position = position;
	}
}
