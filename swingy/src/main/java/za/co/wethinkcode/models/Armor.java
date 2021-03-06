package za.co.wethinkcode.models;

public class Armor extends Artifact {
	private int defense;
	
	public Armor(String name, int defense) {
		super(name);
		this.defense = defense;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public String toString() {
		return "Armor\nName: " + this.name + "\nGrants: " + this.defense + " Defense";
	}
}
