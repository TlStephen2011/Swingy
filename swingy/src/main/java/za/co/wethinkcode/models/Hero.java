package za.co.wethinkcode.models;

import java.util.Random;

import za.co.wethinkcode.utilities.Coordinates;

public class Hero extends Character {
	private String heroClass;
	private int experience;
	private Weapon weapon;
	private Armor armor;
	private Helm helm;
	private Coordinates previousCoordinates;
	
	public Hero(	String name,
					String heroClass,
					int level,
					int experience,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position,
					Weapon weapon,
					Armor armor,
					Helm helm) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);
		
		if (weapon != null) {
			this.weapon = weapon;
			this.attackDamage += this.weapon.getDamage();
		}
		
		if (armor != null) {
			this.armor = armor;
			this.defensePoints += this.armor.getDefense();
		}
		
		if (helm != null) {
			this.helm = helm;
			this.hitPoints += this.helm.getHP();
		}
		
		this.experience = experience;
		this.heroClass = heroClass;
	}
	
	public Hero(	String name,
					String heroClass,
					int level,
					int experience,
					int attackDamage,
					int defensePoints,
					int hitPoints,
					Coordinates position) {
		super(name, level, attackDamage, defensePoints, hitPoints, position);		
		
		this.experience = experience;
		this.heroClass = heroClass;
		
	}
	
	public void equip(Artifact a) {
		if (a instanceof Weapon) {			
			int currentDamageBonus = this.weapon == null ? 0 : this.weapon.getDamage();			
			this.attackDamage = this.attackDamage - currentDamageBonus + ((Weapon)a).getDamage();
			this.weapon = (Weapon) a;
		} else if (a instanceof Armor) {
			int currentArmorBonus = this.armor == null ? 0 : this.armor.getDefense();	
			this.defensePoints = this.defensePoints - currentArmorBonus + ((Armor)a).getDefense();
			this.armor = (Armor) a;
		} else if (a instanceof Helm) {
			int currentHPBonus = this.helm == null ? 0 : this.helm.getHP();	
			this.hitPoints = this.hitPoints - currentHPBonus + ((Helm)a).getHP();
			this.helm = (Helm) a;
		}
	}
	
	public int getXp(int xp) {
		this.experience += xp;
		
		while (this.experience >= (this.level*1000 + Math.pow((this.level - 1), 2)*450)) {
			this.level++;
		}
		
		return this.experience;
	}
	
	public int getDamage() {
		return this.attackDamage;
	}
	
	public void move(Coordinates co) {
		this.previousCoordinates = this.position;
		this.position = co;
	}
	
	public boolean attack(Villain v) {
		//TODO better attack calculation
		if (this.attackDamage > v.attackDamage) {
			return true;
		}
		return false;		
	}
	
	public boolean run(Villain v) {
		//TODO better run algorithm
		Random rand = new Random(System.currentTimeMillis());
		
		if ((rand.nextInt() % 100 + 1) % 2 == 0) {
			this.position = this.previousCoordinates;
			return true;
		}		
		return false;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String toString() {
		String s =  "Hero: " + this.name + "\n" +
					"Class: " + this.heroClass + "\n" +
					"Position: (" + this.position.getRow() + ", " + this.position.getCol() + ")" + "\n";
		return s;
	}
}
