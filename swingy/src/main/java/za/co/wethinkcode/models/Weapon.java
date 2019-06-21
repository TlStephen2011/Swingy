package za.co.wethinkcode.models;

public class Weapon extends Artifact {
	private int damage;
	
	public Weapon(String name, int damage) {
		super(name);
		this.damage = damage;
	}
	
	public int getDamage() {
		return this.damage;
	}
}
