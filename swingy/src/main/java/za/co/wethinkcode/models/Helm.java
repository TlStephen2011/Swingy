package za.co.wethinkcode.models;

public class Helm extends Artifact {
	private int hitPoints;
	
	public Helm(String name, int hp) {
		super(name);
		this.hitPoints = hp;
	}
	
	public int getHP() {
		return this.hitPoints;
	}
}
