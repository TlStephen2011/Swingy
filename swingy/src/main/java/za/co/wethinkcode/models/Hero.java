package za.co.wethinkcode.models;

public class Hero extends Character {
	private String heroClass;
	private int experience;
	
	public String toString() {
		String s =  "Hero: " + this.name + "\n" +
					"Class: " + this.heroClass + "\n" +
					"Position: (" + this.position.getRow() + ", " + this.position.getCol() + ")" + "\n";
		return s;
	}
}
